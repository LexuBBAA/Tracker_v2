package com.tracker.auth.ws.datasources.dtos;

import java.io.Serializable;
import java.sql.Date;

public class TokenDto implements Serializable {
    public Integer id;
    public String token;
    public Date createdAt;
    public Date expiresAt;
    public String deviceId;
}
