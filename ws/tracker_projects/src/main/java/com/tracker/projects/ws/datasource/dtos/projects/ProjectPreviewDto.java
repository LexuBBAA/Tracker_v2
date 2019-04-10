package com.tracker.projects.ws.datasource.dtos.projects;

import com.tracker.projects.ws.datasource.dtos.sprints.SprintDto;
import com.tracker.projects.ws.datasource.dtos.TeamDto;
import com.tracker.projects.ws.datasource.entities.projects.ProjectPreviewEntity;

import java.io.Serializable;
import java.sql.Date;

public class ProjectPreviewDto implements Serializable {
    public Integer id;
    public String title;
    public String description;
    public TeamDto team;
    public String image;
    public Date dueDate;
    public SprintDto currentSprint;
    public ProjectStatus status;

    public ProjectPreviewDto() {
    }

    public ProjectPreviewDto(ProjectPreviewEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();

        this.team = new TeamDto();
        this.team.id = entity.getTeamId();

        this.image = entity.getImage();
        this.dueDate = entity.getDueDate();

        this.currentSprint = new SprintDto();
        this.currentSprint.id = entity.getCurrentSprint();

        this.status = ProjectStatus.valueOf(entity.getProjectStatus());
    }
}
