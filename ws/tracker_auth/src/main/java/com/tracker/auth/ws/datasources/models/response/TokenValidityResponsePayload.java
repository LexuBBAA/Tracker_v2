package com.tracker.auth.ws.datasources.models.response;

import java.io.Serializable;

public class TokenValidityResponsePayload implements Serializable {
    public boolean isValid = false;
    public TokenValidityResponsePayload(boolean isValid) {
        this.isValid = isValid;
    }
}
