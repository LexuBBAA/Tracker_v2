package com.tracker.auth.ws.controllers;

import com.tracker.auth.ws.datasources.dtos.TokenDto;
import com.tracker.auth.ws.datasources.dtos.UserTokenDto;
import com.tracker.auth.ws.datasources.dtos.users.UserEnrollDto;
import com.tracker.auth.ws.datasources.dtos.users.UserEnrollPayloadDto;
import com.tracker.auth.ws.datasources.services.TokensService;
import com.tracker.auth.ws.datasources.services.UserEnrollService;
import com.tracker.auth.ws.datasources.services.UserTokensService;
import com.tracker.auth.ws.utils.UserEnrollValidator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.HeaderParam;
import java.util.regex.Pattern;

@RestController
public class TokensController {
    @Autowired
    private UserTokensService userTokensService;

    @Autowired
    private TokensService tokensService;

    @Autowired
    private UserEnrollService userEnrollService;

    @Autowired
    private UserEnrollValidator userEnrollValidator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    private ResponseEntity login(
            @RequestHeader(value = "password") String password,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "phone", required = false) String phone
    ) {
        boolean validUsername = userEnrollValidator.validateUsername(username);
        boolean validEmail = userEnrollValidator.validateEmail(email);
        boolean validPhone = userEnrollValidator.validatePhoneNo(phone);
        if(!validUsername && !validEmail && !validPhone) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        UserEnrollDto storedUser = null;
        if(validUsername) {
            storedUser = userEnrollService.findByUsername(username);
        }
        if(storedUser == null && validEmail) {
            storedUser = userEnrollService.findByEmail(email);
        }
        if(storedUser == null && validPhone) {
            storedUser = userEnrollService.findByPhone(phone);
        }

        if(storedUser == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        if(passwordEncoder.matches(password, storedUser.getPassword())) {
            UserTokenDto userTokenDto = userTokensService.findByUserId(storedUser.userId);
            if(userTokenDto != null) {
                TokenDto userToken = tokensService.findByTokenId(userTokenDto.tokenId);
                userTokensService.deleteUserToken(storedUser.userId);
                if(userToken != null) {
                    tokensService.deleteToken(userToken.token);
                }
            }

            TokenDto token = tokensService.generateToken();
            if(token == null) {
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            UserTokenDto newToken = userTokensService.createUserToken(storedUser.userId, token);
            if(newToken != null) {
                return new ResponseEntity<>(token, HttpStatus.OK);
            }

            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/register")
    private ResponseEntity register(
            @RequestHeader(value = "createdBy") String createdBy,
            @RequestBody UserEnrollPayloadDto newUser
    ) {
        boolean validEmail = userEnrollValidator.validateEmail(newUser.email);
        boolean validUsername = userEnrollValidator.validateUsername(newUser.username);
        boolean validPassword = userEnrollValidator.validatePassword(newUser.password);
        boolean validPhone = userEnrollValidator.validatePhoneNo(newUser.phone);

        if(!validEmail || !validPassword) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }

        if(userEnrollService.existsByEmail(newUser.email)) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        if(validUsername && userEnrollService.existsByUsername(newUser.username)) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }

        UserEnrollDto newUserDto = new UserEnrollDto();
        newUserDto.email = newUser.email;

        if(validPhone) {
            newUserDto.phone = newUser.phone;
        }

        if(validUsername) {
            newUserDto.username = newUser.username;
        } else if(newUserDto.email != null) {
            newUserDto.username = generateUsernameFromEmail(newUserDto.email);
        }

        newUserDto.setPassword(passwordEncoder.encode(newUser.password));
        UserEnrollDto storedUser = userEnrollService.createUser(newUserDto);

        if(storedUser != null) {
            return new ResponseEntity<>(storedUser, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/validate")
    public ResponseEntity validateToken(
            @RequestHeader(value = "token", required = false) String token,
            @RequestHeader(value = "refreshToken", required = false) String refreshToken
    ) {
        boolean isValid = token != null && tokensService.validateToken(token);
        if(isValid) {
            return new ResponseEntity(HttpStatus.OK);
        }

        if(refreshToken != null) {
            TokenDto storedToken = tokensService.getByRefreshToken(refreshToken);
            if(storedToken.refreshToken.equals(refreshToken)) {
                TokenDto refreshedToken = tokensService.refreshToken(storedToken);
                if(refreshedToken != null) {
                    return new ResponseEntity<>(refreshedToken, HttpStatus.OK);
                }
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/reset")
    public ResponseEntity resetPassword(
            @RequestHeader(value = "email", required = false) String email,
            @RequestHeader(value = "phone", required = false) String phone,
            @RequestHeader(value = "password") String password
    ) {
        boolean validEmail = userEnrollValidator.validateEmail(email);
        boolean validPhone = userEnrollValidator.validatePhoneNo(phone);
        boolean validPassword = userEnrollValidator.validatePassword(password);

        if(!validPassword) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        UserEnrollDto userEnrollDto = null;
        if(validEmail && userEnrollService.existsByEmail(email)) {
            userEnrollDto = userEnrollService.findByEmail(email);
        }

        if(validPhone && userEnrollService.existsByPhone(phone)) {
            userEnrollDto = userEnrollService.findByPhone(phone);
        }

        if(userEnrollDto != null) {
            userEnrollDto.setPassword(password);
            UserEnrollDto updatedUser = userEnrollService.updatePassword(userEnrollDto);

            if(updatedUser != null) {
                return new ResponseEntity(HttpStatus.OK);
            }

            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @NonNull
    private String generateUsernameFromEmail(@NonNull String email) {
        String generatedUsername = email.substring(0, email.indexOf('@')).concat(RandomStringUtils.randomNumeric(5));
        if(userEnrollService.existsByUsername(generatedUsername)) {
            return generateUsernameFromEmail(email);
        }
        return generatedUsername;
    }
}
