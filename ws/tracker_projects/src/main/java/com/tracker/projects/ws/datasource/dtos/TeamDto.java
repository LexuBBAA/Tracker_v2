package com.tracker.projects.ws.datasource.dtos;

import com.tracker.projects.ws.datasource.entities.TeamEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TeamDto implements Serializable {

    private static final long serialVersionUID = -9021077517157602896L;

    public Long id;
    public String teamId;
    public String name;
    public String description;
    public String createdBy;
    public LocalDateTime createdDate;

    public TeamDto() {
    }

    public TeamDto(TeamEntity entity) {
        this.id = entity.getId();
        this.teamId = entity.getTeamId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.createdBy = entity.getCreatedBy();
        this.createdDate = entity.getCreatedDate();
    }
}
