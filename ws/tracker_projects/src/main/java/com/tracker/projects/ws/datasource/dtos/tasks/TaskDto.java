package com.tracker.projects.ws.datasource.dtos.tasks;

import com.tracker.projects.ws.datasource.entities.tasks.TaskEntity;

import java.time.LocalDateTime;

public class TaskDto extends TaskPreviewDto {
    private static final long serialVersionUID = 4839049438250665624L;

    public String description;
    public String createdBy;
    public LocalDateTime createdAt;
    public LocalDateTime lastUpdatedAt;
    public String lastModifiedBy;
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
        this.lastModifiedBy = entity.getLastModifiedBy();
        this.estimate = entity.getEstimate();
        this.logged = entity.getLogged();
        this.storyPoints = entity.getStoryPoints();
        this.isEpic = entity.isEpic();
        this.sprint = entity.getSprint();
        this.project = entity.getProject();
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "description='" + description + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", lastUpdatedAt=" + lastUpdatedAt +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", estimate=" + estimate +
                ", logged=" + logged +
                ", storyPoints=" + storyPoints +
                ", isEpic=" + isEpic +
                ", sprint='" + sprint + '\'' +
                ", project='" + project + '\'' +
                ", parent=" + parent +
                ", epic=" + epic +
                ", partOf=" + partOf +
                ", subtaskOf=" + subtaskOf +
                ", blocks=" + blocks +
                ", id=" + id +
                ", taskId='" + taskId + '\'' +
                ", title='" + title + '\'' +
                ", assignedTo='" + assignedTo + '\'' +
                ", dueDate=" + dueDate +
                ", type=" + type +
                ", status=" + status +
                ", priority=" + priority +
                '}';
    }
}
