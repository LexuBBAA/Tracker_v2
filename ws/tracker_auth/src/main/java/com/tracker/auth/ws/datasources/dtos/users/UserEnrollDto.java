package com.tracker.auth.ws.datasources.dtos.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tracker.auth.ws.datasources.entities.UserEnrollEntity;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserEnrollDto implements Serializable {
    private static final long serialVersionUID = -6849834163268685728L;

    public static final String DEFAULT_PHONE = "0000000000";
    public static final String DEFAULT_FIRST_NAME = "Default";
    public static final String DEFAULT_LAST_NAME = "Default";

    public String userId;
    public String username;
    public String email;
    public String phone;

    private String firstName;
    private String lastName;

    private String password;

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonIgnore
    public String getLastName() {
        return lastName;
    }

    @JsonProperty
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "UserEnrollDto{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
