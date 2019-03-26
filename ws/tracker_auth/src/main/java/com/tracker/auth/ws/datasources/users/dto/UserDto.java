package com.tracker.auth.ws.datasources.users.dto;

import java.io.Serializable;
import java.sql.Date;

public class UserDto implements Serializable {
    //  User Fields
    public int userId;
    public String username;
    public String password;
    public String email;
    public String phoneNo;

    //  Device Fields
    public String deviceId;
    public String deviceOS;
    public String deviceOsVersion;
    public Float deviceLastLat;
    public Float deviceLastLon;
    public Date deviceLastKnownInteraction;
    public Date deviceRegisteredDate;

    public UserDto() {
    }
}
