package com.tracker.projects.ws.datasource.dtos.sprints;

import com.tracker.projects.ws.datasource.entities.sprints.SprintEntity;

import java.io.Serializable;

public class SprintPreviewDto implements Serializable {
    private static final long serialVersionUID = -219586007118700872L;

    public Long id;
    public String sprintId;
    public String title;
    public SprintStatusDto status;

    public SprintPreviewDto() {
    }

    public SprintPreviewDto(SprintEntity entity) {
        this.id = entity.getId();
        this.sprintId = entity.getSprintId();
        this.title = entity.getTitle();
        this.status = new SprintStatusDto(entity.getStatus());
    }
}
