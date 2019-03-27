package com.tracker.ws.entities.responses.auth;

import java.io.Serializable;
import java.sql.Date;

public class TokenResponsePayload implements Serializable {
    public String token;
    public Date expiresAt;
}
