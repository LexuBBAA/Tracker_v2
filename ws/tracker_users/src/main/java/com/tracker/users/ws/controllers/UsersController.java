package com.tracker.users.ws.controllers;

import com.tracker.users.ws.datasource.dto.UserDto;
import com.tracker.users.ws.datasource.requests.auth.AuthTokenOwnerRequest;
import com.tracker.users.ws.datasource.requests.roles.GetUserRoleRequest;
import com.tracker.users.ws.datasource.responses.auth.AuthTokenOwnerRespose;
import com.tracker.users.ws.datasource.responses.roles.UserRoleResponse;
import com.tracker.users.ws.datasource.services.UsersPreviewService;
import com.tracker.users.ws.datasource.services.impl.UsersPreviewServiceImpl;
import com.tracker.users.ws.datasource.services.impl.UsersServiceImpl;
import com.tracker.users.ws.utils.HttpResponseCode;
import com.tracker.users.ws.utils.HttpResponseMessage;
import com.tracker.users.ws.utils.RequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
public class UsersController {
    private static final String AUTH_URL = "http://tracker-auth";
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

    @GetMapping("/user/{userId}")
    public ResponseEntity getUserDetails(
            @RequestHeader(value = "token") String token,
            @PathVariable(name = "userId") Integer userId
    ) {
        if (token == null || token.isEmpty()) {
            return new ResponseEntity<>(
                    HttpResponseMessage.get(HttpResponseCode.HTTP_RESPONSE_FORBIDDEN).getValue(),
                    HttpResponseCode.HTTP_RESPONSE_FORBIDDEN.getHttpStatus()
            );
        }


        ResponseEntity<AuthTokenOwnerRespose> tokenOwnerResponse = restTemplate.exchange(AUTH_URL + "/oauth/owner/" + token, HttpMethod.GET, new AuthTokenOwnerRequest(token), AuthTokenOwnerRespose.class);
        AuthTokenOwnerRespose tokenOwner = tokenOwnerResponse.getBody();

        UserDto userDetails = usersService.getUserById(userId);
        if (userDetails != null && (int) userDetails.userId == Objects.requireNonNull(tokenOwner).userId) {
            return new ResponseEntity<>(userDetails, HttpStatus.OK);
        } else if (userDetails == null) {
            return new ResponseEntity<>(
                    HttpResponseMessage.get(HttpResponseCode.HTTP_RESPONSE_NO_CONTENT),
                    HttpResponseCode.HTTP_RESPONSE_NO_CONTENT.getHttpStatus()
            );
        }

        ResponseEntity<UserRoleResponse> tokenOwnerRoleResponse = restTemplate.exchange(ROLES_URL + "/role/full/" + Objects.requireNonNull(tokenOwner).userId, HttpMethod.GET, new GetUserRoleRequest(), UserRoleResponse.class);
        userDetails.formatForRole(tokenOwnerRoleResponse.getBody(), RequestType.VIEW);
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/preview")
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

}