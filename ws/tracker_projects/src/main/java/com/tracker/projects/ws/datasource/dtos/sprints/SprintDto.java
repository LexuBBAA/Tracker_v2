package com.tracker.projects.ws.datasource.dtos.sprints;

import com.tracker.projects.ws.datasource.entities.sprints.SprintEntity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SprintDto implements Serializable {
    private static final long serialVersionUID = -1869049001997726986L;

    public Long id;
    public String sprintId;
    public String title;
    public String description;
    public LocalDate startDate;
    public LocalDate endDate;
    public String createdBy;
    public LocalDateTime createdDate;
    public String project;
    public SprintStatusDto status;

    public SprintDto() {
    }

    public SprintDto(SprintEntity entity) {
        this.id = entity.getId();
        this.sprintId = entity.getSprintId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.createdBy = entity.getCreatedBy();
        this.createdDate = entity.getCreatedDate();
        this.project = entity.getProject();
        this.status = new SprintStatusDto(entity.getStatus());
    }
}
