package com.tracker.users.ws.datasource.requests.roles;

import com.tracker.users.ws.TrackerUsersApplication;
import org.springframework.http.HttpEntity;

public class GetUserRoleRequest extends HttpEntity {
    public GetUserRoleRequest() {
        this.getHeaders().add("internal", TrackerUsersApplication.class.getSimpleName());
    }
}
