package com.tracker.projects.ws.datasource.dtos;

import com.tracker.projects.ws.datasource.entities.ProjectEntity;
import com.tracker.projects.ws.datasource.entities.ProjectPreviewEntity;

import java.io.File;
import java.sql.Date;

public class ProjectDto extends ProjectPreviewDto {
    public Integer id;
    public String title;
    public TeamDto team;
    public String description;
    public String image;
    public Integer clientId;
    public Date startDate;
    public Date dueDate;
    public SprintDto currentSprint;
    public Integer sprintDuration;
    public Integer sprintPlanningDay;
    public Integer sprintDemoDay;
    public Integer sprintReviewDay;
    public Double costValue;
    public String attachment;
    public ProjectStatus status;

    public ProjectDto() {
    }

    public ProjectDto(ProjectEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();

        this.team = new TeamDto();
        this.team.id = entity.getTeamId();

        this.image = entity.getImage();
        this.clientId = entity.getClientId();
        this.startDate = entity.getStartDate();
        this.dueDate = entity.getDueDate();

        this.currentSprint = new SprintDto();
        this.currentSprint.id = entity.getCurrentSprint();
        this.sprintDuration = entity.getSprintDuration();
        this.sprintPlanningDay = entity.getSprintPlanningDay();
        this.sprintDemoDay = entity.getSprintDemoDay();
        this.sprintReviewDay = entity.getSprintReviewDay();

        this.costValue = entity.getCostValue();
        this.attachment = entity.getAttachment();

        this.status = ProjectStatus.valueOf(entity.getProjectStatus());
    }
}
