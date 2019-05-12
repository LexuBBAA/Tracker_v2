package com.tracker.projects.ws.datasource.dtos.sprints;

import com.tracker.projects.ws.datasource.entities.sprints.SprintEntity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SprintDto extends SprintPreviewDto {
    private static final long serialVersionUID = -1869049001997726986L;

    public String description;
    public LocalDate startDate;
    public LocalDate endDate;
    public String createdBy;
    public LocalDateTime createdDate;
    public String project;

    public SprintDto() {
        super();
    }

    public SprintDto(SprintEntity entity) {
        super(entity);
        this.description = entity.getDescription();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.createdBy = entity.getCreatedBy();
        this.createdDate = entity.getCreatedDate();
        this.project = entity.getProject();
    }
}
