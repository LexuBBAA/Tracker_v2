package com.tracker.auth.ws.datasources.entities;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;

@Entity
@Table(name = "accesstokens")
public class TokenEntity implements Serializable {
    private static final long serialVersionUID = 3576701647981514881L;
    private static final int TOKEN_LIFE_SPAN_DAYS = 30;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "token", nullable = false, unique = true, length = 100)
    private String token;

    @NonNull
    @Column(name = "expiresat", nullable = false)
    private LocalDateTime expiresAt;

    @NonNull
    @Column(name = "refreshtoken", nullable = false, unique = true, length = 100)
    private String refreshToken;

    public TokenEntity() {}

    public TokenEntity(@NonNull String token, @NonNull String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.expiresAt = LocalDateTime.now().plusDays(TOKEN_LIFE_SPAN_DAYS);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getToken() {
        return token;
    }

    public void setToken(@NonNull String token) {
        this.token = token;
        this.expiresAt = LocalDateTime.now().plusDays(TOKEN_LIFE_SPAN_DAYS);
    }

    @NonNull
    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(@NonNull LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    @NonNull
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(@NonNull String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
