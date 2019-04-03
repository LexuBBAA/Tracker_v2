package com.tracker.ws.controllers;

import com.tracker.ws.entities.requests.users.CreateUserRequestPayload;
import com.tracker.ws.entities.requests.users.UpdateUserRequestPayload;
import com.tracker.ws.entities.responses.auth.TokenValidityResponsePayload;
import com.tracker.ws.entities.responses.base.ErrorResponse;
import com.tracker.ws.entities.responses.enums.HttpResponseCode;
import com.tracker.ws.entities.responses.enums.HttpResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class UsersController {
    private static final String USERS_URL = "http://tracker-users";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/users")
    public ResponseEntity getUsers(
            @RequestHeader(value = "client-secret", required = false) String clientSecret,
            @RequestHeader(value = "client-id", required = false) String clientId,
            @RequestHeader(value = "token", required = false) String token
    ) {
        if(clientId == null || clientSecret == null) {
            return new ErrorResponse(HttpResponseCode.HTTP_RESPONSE_FORBIDDEN).toResponseEntity();
        }

        if(token == null) {
            return new ErrorResponse(HttpResponseCode.HTTP_RESPONSE_FORBIDDEN).toResponseEntity();
        }

        return restTemplate.exchange(USERS_URL + "/", HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), Object.class);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity getUserDetails(
            @RequestHeader(value = "client-secret", required = false) String clientSecret,
            @RequestHeader(value = "client-id", required = false) String clientId,
            @RequestHeader(value = "token", required = false) String token,
            @PathVariable(name = "userId") Integer userId
    ) {
        if(clientId == null || clientSecret == null) {
            return new ErrorResponse(HttpResponseCode.HTTP_RESPONSE_FORBIDDEN).toResponseEntity();
        }

        if(userId == null) {
            return new ResponseEntity<>(
                    HttpResponseMessage.get(HttpResponseCode.HTTP_RESPONSE_BAD_REQUEST),
                    HttpStatus.BAD_REQUEST
            );
        }

        ResponseEntity validateTokenResponse = new AuthController().validateToken(clientSecret, clientId, token);
        if(!(validateTokenResponse.getBody() instanceof TokenValidityResponsePayload) || !((TokenValidityResponsePayload) validateTokenResponse.getBody()).isValid) {
            return new ResponseEntity<>(
                    "Invalid Token",
                    HttpStatus.BAD_REQUEST
            );
        }
        return restTemplate.exchange(USERS_URL + "/" + userId, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), Object.class);
    }

    @GetMapping("/user/{userId}/preview")
    public ResponseEntity getUserPreview(
            @RequestHeader(value = "client-secret", required = false) String clientSecret,
            @RequestHeader(value = "client-id", required = false) String clientId,
            @RequestHeader(value = "token", required = false) String token,
            @PathVariable(name = "userId", required = false) Integer userId
    ) {
        if(clientId == null || clientSecret == null) {
            return new ErrorResponse(HttpResponseCode.HTTP_RESPONSE_FORBIDDEN).toResponseEntity();
        }

        if(userId == null) {
            return new ResponseEntity<>(
                    HttpResponseMessage.get(HttpResponseCode.HTTP_RESPONSE_BAD_REQUEST),
                    HttpStatus.BAD_REQUEST
            );
        }

        ResponseEntity validateTokenResponse = new AuthController().validateToken(clientSecret, clientId, token);
        if(!(validateTokenResponse.getBody() instanceof TokenValidityResponsePayload) || !((TokenValidityResponsePayload) validateTokenResponse.getBody()).isValid) {
            return new ResponseEntity<>(
                    "Invalid Token",
                    HttpStatus.BAD_REQUEST
            );
        }
        return restTemplate.exchange(USERS_URL + "/" + userId + "/preview", HttpMethod.GET, new HttpEntity(new HttpHeaders()), Object.class);
    }

    @PostMapping("/user")
    public ResponseEntity createUser(
            @RequestHeader(value = "client-secret", required = false) String clientSecret,
            @RequestHeader(value = "client-id", required = false) String clientId,
            @RequestHeader(value = "token", required = false) String token,
            @RequestBody CreateUserRequestPayload newUser
    ) {
        if(clientId == null || clientSecret == null) {
            return new ErrorResponse(HttpResponseCode.HTTP_RESPONSE_FORBIDDEN).toResponseEntity();
        }

        ResponseEntity validateTokenResponse = new AuthController().validateToken(clientSecret, clientId, token);
        if(!(validateTokenResponse.getBody() instanceof TokenValidityResponsePayload) || !((TokenValidityResponsePayload) validateTokenResponse.getBody()).isValid) {
            return new ResponseEntity<>(
                    "Invalid Token",
                    HttpStatus.BAD_REQUEST
            );
        }
        HttpEntity<CreateUserRequestPayload> httpEntity = new HttpEntity<>(newUser, new HttpHeaders());
        return restTemplate.exchange(USERS_URL + "/", HttpMethod.POST, httpEntity, Object.class);
    }

    @PutMapping("/user")
    public ResponseEntity updateUser(
            @RequestHeader(value = "client-secret", required = false) String clientSecret,
            @RequestHeader(value = "client-id", required = false) String clientId,
            @RequestHeader(value = "token", required = false) String token,
            @RequestBody UpdateUserRequestPayload updatedUser
    ) {
        if(clientId == null || clientSecret == null) {
            return new ErrorResponse(HttpResponseCode.HTTP_RESPONSE_FORBIDDEN).toResponseEntity();
        }

        ResponseEntity validateTokenResponse = new AuthController().validateToken(clientSecret, clientId, token);
        if(!(validateTokenResponse.getBody() instanceof TokenValidityResponsePayload) || !((TokenValidityResponsePayload) validateTokenResponse.getBody()).isValid) {
            return new ResponseEntity<>(
                    "Invalid Token",
                    HttpStatus.BAD_REQUEST
            );
        }
        HttpEntity<UpdateUserRequestPayload> httpEntity = new HttpEntity<>(updatedUser, new HttpHeaders());
        return restTemplate.exchange(USERS_URL + "/", HttpMethod.PUT, httpEntity, Object.class);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity deleteUser(
            @RequestHeader(value = "client-secret", required = false) String clientSecret,
            @RequestHeader(value = "client-id", required = false) String clientId,
            @RequestHeader(value = "token", required = false) String token,
            @PathVariable(value = "userId") Integer userId
    ) {
        if(clientId == null || clientSecret == null) {
            return new ErrorResponse(HttpResponseCode.HTTP_RESPONSE_FORBIDDEN).toResponseEntity();
        }

        if(userId == null) {
            return new ResponseEntity<>(
                    HttpResponseMessage.get(HttpResponseCode.HTTP_RESPONSE_BAD_REQUEST),
                    HttpStatus.BAD_REQUEST
            );
        }

        ResponseEntity validateTokenResponse = new AuthController().validateToken(clientSecret, clientId, token);
        if(!(validateTokenResponse.getBody() instanceof TokenValidityResponsePayload) || !((TokenValidityResponsePayload) validateTokenResponse.getBody()).isValid) {
            return new ResponseEntity<>(
                    "Invalid Token",
                    HttpStatus.BAD_REQUEST
            );
        }
        return restTemplate.exchange(USERS_URL + "/" + userId, HttpMethod.DELETE, new HttpEntity<>(new HttpHeaders()), Object.class);
    }
}
