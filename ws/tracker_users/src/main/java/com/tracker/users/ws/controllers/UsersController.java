package com.tracker.users.ws.controllers;

import com.tracker.users.ws.datasource.dto.UserDto;
import com.tracker.users.ws.datasource.responses.roles.UserRoleResponse;
import com.tracker.users.ws.datasource.responses.self.CreateUserResponseBody;
import com.tracker.users.ws.datasource.services.impl.UsersPreviewServiceImpl;
import com.tracker.users.ws.datasource.services.impl.UsersServiceImpl;
import com.tracker.users.ws.utils.HttpResponseCode;
import com.tracker.users.ws.utils.HttpResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class UsersController {
    private static final String ROLES_URL = "http://tracker-roles";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UsersServiceImpl usersService;

    @Autowired
    private UsersPreviewServiceImpl usersPreviewService;

    @GetMapping("/")
    public String getStatus() {
        return "Available";
    }

    @GetMapping("/users")
    public ResponseEntity getUsers() {
        List<UserDto> users = usersService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity getUserDetails(
            @PathVariable(name = "userId") Integer userId
    ) {
        UserDto userDetails = usersService.getUserById(userId);

        if (userDetails == null) {
            return new ResponseEntity<>(
                    HttpResponseMessage.get(HttpResponseCode.HTTP_RESPONSE_NO_CONTENT),
                    HttpResponseCode.HTTP_RESPONSE_NO_CONTENT.getHttpStatus()
            );
        } else {
            ResponseEntity<UserRoleResponse> roleResponse = restTemplate.exchange(ROLES_URL + "/" + userDetails.role.id, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), UserRoleResponse.class);
            userDetails.role = roleResponse.getBody();

            return new ResponseEntity<>(userDetails, HttpStatus.OK);
        }
    }

    @GetMapping("/{userId}/preview")
    public ResponseEntity getUserPreview(
            @PathVariable(value = "userId") Integer userId
    ) {
        UserDto userDto = usersPreviewService.getUserById(userId);
        if(userDto == null) {
            return new ResponseEntity<>(
                    HttpResponseMessage.get(HttpResponseCode.HTTP_RESPONSE_NO_CONTENT),
                    HttpResponseCode.HTTP_RESPONSE_NO_CONTENT.getHttpStatus()
            );
        }
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity createNewUser(@RequestBody UserDto newUser) {
        if(newUser == null || newUser.validateRequestMandatoryFields()) {
            return new ResponseEntity<>(
                    HttpResponseMessage.get(HttpResponseCode.HTTP_RESPONSE_BAD_REQUEST),
                    HttpResponseCode.HTTP_RESPONSE_BAD_REQUEST.getHttpStatus()
            );
        }

        UserDto userDto = usersService.createUser(newUser);
        if(userDto != null && userDto.userId != null) {
            CreateUserResponseBody responseBody = new CreateUserResponseBody(userDto);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    HttpResponseMessage.get(HttpResponseCode.HTTP_RESPONSE_INTERNAL_SERVER_ERROR),
                    HttpResponseCode.HTTP_RESPONSE_INTERNAL_SERVER_ERROR.getHttpStatus()
            );
        }
    }

    @PutMapping("/")
    public ResponseEntity updateUser(@RequestBody UserDto user) {
        if(user == null || user.userId == null || user.validateRequestMandatoryFields()) {
            return new ResponseEntity<>(
                    HttpResponseMessage.get(HttpResponseCode.HTTP_RESPONSE_BAD_REQUEST),
                    HttpResponseCode.HTTP_RESPONSE_BAD_REQUEST.getHttpStatus()
            );
        }

        UserDto newUser = usersService.updateUser(user);
        if(newUser == null) {
            return new ResponseEntity<>(
                    HttpResponseMessage.get(HttpResponseCode.HTTP_RESPONSE_INTERNAL_SERVER_ERROR),
                    HttpResponseCode.HTTP_RESPONSE_INTERNAL_SERVER_ERROR.getHttpStatus()
            );
        } else {
            return new ResponseEntity<>(
                    HttpResponseMessage.get(HttpResponseCode.HTTP_RESPONSE_OK),
                    HttpResponseCode.HTTP_RESPONSE_OK.getHttpStatus()
            );
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable(value = "userId") Integer userId) {
        UserDto userDto = usersService.getUserById(userId);
        if(userDto == null) {
            return new ResponseEntity<>(
                    HttpResponseMessage.get(HttpResponseCode.HTTP_RESPONSE_NO_CONTENT),
                    HttpResponseCode.HTTP_RESPONSE_NO_CONTENT.getHttpStatus()
            );
        }

        usersService.deleteUserById(userId);
        return new ResponseEntity<>(
                HttpResponseMessage.get(HttpResponseCode.HTTP_RESPONSE_OK),
                HttpResponseCode.HTTP_RESPONSE_OK.getHttpStatus()
        );
    }

}