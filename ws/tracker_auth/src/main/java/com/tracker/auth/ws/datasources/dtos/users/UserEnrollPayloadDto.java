package com.tracker.auth.ws.datasources.dtos.users;

import java.io.Serializable;

public class UserEnrollPayloadDto implements Serializable {
    private static final long serialVersionUID = 1576112626173477690L;

    public String username;
    public String email;
    public String phone;
    public String password;

    @Override
    public String toString() {
        return "UserEnrollPayloadDto{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
