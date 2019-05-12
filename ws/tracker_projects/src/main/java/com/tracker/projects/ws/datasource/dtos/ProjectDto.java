package com.tracker.projects.ws.datasource.dtos;

import com.tracker.projects.ws.datasource.dtos.sprints.SprintPreviewDto;
import com.tracker.projects.ws.datasource.entities.ProjectEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ProjectDto implements Serializable {
    private static final long serialVersionUID = 2311713209619589045L;

    public Long id;
    public String projectId;
    public String title;
    public String description;
    public String createdBy;
    public LocalDateTime createdDate;
    public SprintPreviewDto activeSprint;
    public String assignedTeam;

    public ProjectDto() {
    }

    public ProjectDto(ProjectEntity entity) {
        this.id = entity.getId();
        this.projectId = entity.getProjectId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.createdBy = entity.getCreatedBy();
        this.createdDate = entity.getCreatedDate();
        this.assignedTeam = entity.getAssignedTeam();
    }
}
