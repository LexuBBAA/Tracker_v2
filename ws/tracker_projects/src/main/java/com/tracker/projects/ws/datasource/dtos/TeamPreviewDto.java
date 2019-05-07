package com.tracker.projects.ws.datasource.dtos;

import com.tracker.projects.ws.datasource.entities.TeamEntity;

import java.io.Serializable;

public class TeamPreviewDto implements Serializable {
    private static final long serialVersionUID = 7843581669078486441L;

    public Long id;
    public String teamId;
    public String name;
    public String description;

    public TeamPreviewDto() {
    }

    public TeamPreviewDto(TeamEntity entity) {
        this.id = entity.getId();
        this.teamId = entity.getTeamId();
        this.name = entity.getName();
        this.description = entity.getDescription();
    }
}
