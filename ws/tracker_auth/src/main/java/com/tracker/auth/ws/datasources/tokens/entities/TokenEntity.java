package com.tracker.auth.ws.datasources.tokens.entities;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

@Entity
@Table(name = "Tokens")
public class TokenEntity {
    private static final int TOKEN_LIFE_SPAN_DAYS = 30;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NonNull
    @Column(name = "token", nullable = false)
    private String token;

    @NonNull
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @NonNull
    @Column(name = "expires_at", nullable = false)
    private Date expiresAt;

    @NonNull
    @Column(name = "device_id", nullable = false)
    private String deviceId;

    public TokenEntity() {}

    public TokenEntity(@NonNull String token, @NonNull String deviceId) {
        this.token = token;
        this.deviceId = deviceId;

        Date currentDate = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, TOKEN_LIFE_SPAN_DAYS);

        this.createdAt = new Date(System.currentTimeMillis());
        this.expiresAt = new Date(calendar.getTime().getTime());
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public String getDeviceId() {
        return deviceId;
    }
}
