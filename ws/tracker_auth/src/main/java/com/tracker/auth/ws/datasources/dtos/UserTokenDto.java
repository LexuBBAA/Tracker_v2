package com.tracker.auth.ws.datasources.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tracker.auth.ws.datasources.entities.UserTokenEntity;

import java.io.Serializable;

public class UserTokenDto implements Serializable {
    private static final long serialVersionUID = -7141433364773013556L;

    @JsonIgnore
    public Long id;
    public Long tokenId;
    public String userId;

    public UserTokenDto(UserTokenEntity entity) {
        this.id = entity.getId();
        this.tokenId = entity.getTokenId();
        this.userId = entity.getUserId();
    }

    @Override
    public String toString() {
        return "UserTokenDto{" +
                "id=" + id +
                ", tokenId=" + tokenId +
                ", userId='" + userId + '\'' +
                '}';
    }
}
