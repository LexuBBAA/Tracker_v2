package com.tracker.projects.ws.datasource.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "UserTeams")
@DynamicUpdate
public class UserTeamEntity implements Serializable {
    private static final long serialVersionUID = 8693170962108013081L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "userid", nullable = false, updatable = false)
    private String userId;

    @Column(name = "teamid", nullable = false, updatable = false)
    private String teamId;

    @Column(name = "joineddate", updatable = false)
    @CreationTimestamp
    private LocalDateTime joinedDate;

    public UserTeamEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public LocalDateTime getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDateTime joinedDate) {
        this.joinedDate = joinedDate;
    }
}
