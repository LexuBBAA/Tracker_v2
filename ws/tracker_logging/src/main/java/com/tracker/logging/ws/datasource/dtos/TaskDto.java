package com.tracker.logging.ws.datasource.dtos;

import com.tracker.logging.ws.datasource.entities.TaskEntity;

import java.time.LocalDateTime;

public class TaskDto extends TaskPreviewDto {
    private static final long serialVersionUID = 4839049438250665624L;

    public String description;
    public String createdBy;
    public LocalDateTime createdAt;
    public LocalDateTime lastUpdatedAt;
    public Double estimate;
    public Double logged;
    public Integer storyPoints;
    public boolean isEpic;
    public String sprint;
    public String project;

    public TaskPreviewDto parent;
    public TaskPreviewDto epic;
    public TaskPreviewDto partOf;
    public TaskPreviewDto subtaskOf;
    public TaskPreviewDto blocks;

    public TaskDto() {
        super();
    }

    public TaskDto(TaskEntity entity) {
        super(entity);
        this.description = entity.getDescription();
        this.createdBy = entity.getCreatedBy();
        this.createdAt = entity.getCreatedAt();
        this.lastUpdatedAt = entity.getUpdatedAt();
        this.estimate = entity.getEstimate();
        this.logged = entity.getLogged();
        this.storyPoints = entity.getStoryPoints();
        this.isEpic = entity.isEpic();
        this.sprint = entity.getSprint();
        this.project = entity.getProject();
    }
}
