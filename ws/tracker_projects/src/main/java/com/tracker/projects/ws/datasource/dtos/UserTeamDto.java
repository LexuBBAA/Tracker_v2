package com.tracker.projects.ws.datasource.dtos;

import com.tracker.projects.ws.datasource.entities.UserTeamEntity;

import java.io.Serializable;
import java.time.LocalDate;

public class UserTeamDto implements Serializable {
    private static final long serialVersionUID = -7699787397707442389L;

    public Long id;
    public String userId;
    public String teamId;
    public LocalDate joinedDate;

    public UserTeamDto(UserTeamEntity entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.teamId = entity.getTeamId();
        this.joinedDate = entity.getJoinedDate();
    }

    public UserTeamDto(String userId, String teamId) {
        this.userId = userId;
        this.teamId = teamId;
    }
}
