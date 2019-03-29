package com.tracker.users.ws.datasource.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "Users")
public class UserPreviewEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "username")
    private String username;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "role")
    private Integer roleId;
    @Column(name = "last_activity")
    private Date lastActivityTime;

    public UserPreviewEntity() {
    }

    public Integer getUserId() {
        return userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public Date getLastActivityTime() {
        return lastActivityTime;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setLastActivityTime(Date lastActivityTime) {
        this.lastActivityTime = lastActivityTime;
    }
}
