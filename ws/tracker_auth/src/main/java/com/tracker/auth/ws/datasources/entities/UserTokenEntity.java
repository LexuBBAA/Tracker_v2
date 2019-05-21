package com.tracker.auth.ws.datasources.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usertokens")
public class UserTokenEntity implements Serializable {
    private static final long serialVersionUID = 9170980448777821675L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "tokenid", nullable = false, updatable = false, unique = true)
    private Long tokenId;
    @Column(name = "userid", nullable = false, updatable = false)
    private String userId;

    public UserTokenEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTokenId() {
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserTokenEntity{" +
                "id=" + id +
                ", tokenId=" + tokenId +
                ", userId='" + userId + '\'' +
                '}';
    }
}
