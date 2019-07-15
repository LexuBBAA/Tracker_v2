package com.tracker.auth.ws.datasources.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tracker.auth.ws.datasources.entities.TokenEntity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

public class TokenDto implements Serializable {
    private static final long serialVersionUID = -4980535220593513271L;

    @JsonIgnore
    public Long id;
    public String token;
    public String refreshToken;
    public LocalDateTime expiresAt;

    public TokenDto(TokenEntity entity) {
        this.id = entity.getId();
        this.token = entity.getToken();
        this.refreshToken = entity.getRefreshToken();
        this.expiresAt = entity.getExpiresAt();
    }

    @Override
    public String toString() {
        return "TokenDto{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expiresAt=" + expiresAt +
                '}';
    }
}
