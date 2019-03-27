package com.tracker.ws.controllers;

import com.tracker.ws.entities.responses.base.ErrorResponse;
import com.tracker.ws.entities.responses.auth.TokenResponsePayload;
import com.tracker.ws.entities.responses.enums.HttpResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AuthController {

    private static final String AUTH_URL = "http://tracker-auth";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/oauth")
    public ResponseEntity getStatus(
            @RequestHeader(value = "client-secret", required = false) String clientSecret,
            @RequestHeader(value = "client-id", required = false) String clientId
    ) {
        if(clientId == null || clientSecret == null) {
            return new ErrorResponse(HttpResponseCode.HTTP_RESPONSE_FORBIDDEN).toResponseEntity();
        }

        HttpHeaders statusRequestHeaders = new HttpHeaders();
        statusRequestHeaders.add("internal", AuthController.class.getSimpleName());
        HttpEntity statusRequest = new HttpEntity(statusRequestHeaders);
        return restTemplate.exchange(AUTH_URL + "/", HttpMethod.GET, statusRequest, String.class);
    }

    @GetMapping("/oauth/token")
    public ResponseEntity getToken(
            @RequestHeader(value = "username", required = false) String username,
            @RequestHeader(value = "password", required = false) String password,
            @RequestHeader(value = "client-secret", required = false) String clientSecret,
            @RequestHeader(value = "client-id", required = false) String clientId,
            @RequestHeader(value = "deviceId", required = false) String deviceId,
            @RequestHeader(value = "deviceOs", required = false) String deviceOs,
            @RequestHeader(value = "deviceOsVersion", required = false) String deviceOsVersion,
            @RequestHeader(value = "latitude", required = false) Float latitude,
            @RequestHeader(value = "longitude", required = false) Float longitude
    ) {
        if(clientId == null || clientSecret == null) {
            return new ErrorResponse(HttpResponseCode.HTTP_RESPONSE_FORBIDDEN).toResponseEntity();
        }

        if(deviceId == null) {
            return new ErrorResponse(HttpResponseCode.HTTP_RESPONSE_BAD_REQUEST).toResponseEntity();
        }

        HttpHeaders authHeaders = new HttpHeaders();
        authHeaders.add("username", username);
        authHeaders.add("password", password);
        authHeaders.add("deviceId", deviceId);
        authHeaders.add("deviceOs", deviceOs);
        authHeaders.add("deviceOsVersion", deviceOsVersion);
        if(latitude != null) {
            authHeaders.add("latitude", String.valueOf(latitude));
        }
        if(longitude != null) {
            authHeaders.add("longitude", String.valueOf(longitude));
        }
        authHeaders.add("internal", AuthController.class.getSimpleName());
        HttpEntity authRequestTemplate = new HttpEntity(authHeaders);

        return restTemplate.exchange(AUTH_URL + "/token", HttpMethod.GET, authRequestTemplate, TokenResponsePayload.class);
    }

}
