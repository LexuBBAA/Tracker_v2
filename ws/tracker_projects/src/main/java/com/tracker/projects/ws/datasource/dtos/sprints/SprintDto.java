package com.tracker.projects.ws.datasource.dtos.sprints;

import com.tracker.projects.ws.datasource.entities.SprintEntity;

import java.io.Serializable;
import java.sql.Date;

public class SprintDto implements Serializable {
    public Integer id;
    public Integer parentProjectId;
    public String title;
    public String description;
    public Date startDate;
    public Date endDate;
    public Integer totalStoryPoints;
    public SprintStatus sprintStatus;

    public SprintDto() {
    }

    public SprintDto(SprintEntity entity) {
        this.id = entity.getId();
        this.parentProjectId = entity.getParentProjectId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.totalStoryPoints = entity.getTotalStoryPoints();
        this.sprintStatus = SprintStatus.valueOf(entity.getSprintStatus());
    }
}
