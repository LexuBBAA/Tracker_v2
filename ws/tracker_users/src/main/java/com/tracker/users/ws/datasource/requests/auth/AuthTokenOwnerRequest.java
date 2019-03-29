package com.tracker.users.ws.datasource.requests.auth;

import com.tracker.users.ws.TrackerUsersApplication;
import org.springframework.http.HttpEntity;

public class AuthTokenOwnerRequest extends HttpEntity {
    public AuthTokenOwnerRequest(String token) {
        this.getHeaders().add("token", token);
        this.getHeaders().add("internal", TrackerUsersApplication.class.getSimpleName());
    }
}
