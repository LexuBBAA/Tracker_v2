package com.tracker.projects.ws.datasource.dtos.tasks;

import com.tracker.projects.ws.datasource.entities.tasks.TaskEntity;

import java.io.Serializable;
import java.time.LocalDate;

public class TaskPreviewDto implements Serializable {
    private static final long serialVersionUID = -1279980678059418089L;

    public Long id;
    public String taskId;
    public String title;
    public String assignedTo;
    public LocalDate dueDate;
    public TaskTypeDto type;
    public TaskStatusDto status;
    public TaskPriorityDto priority;

    public TaskPreviewDto() {
    }

    public TaskPreviewDto(TaskEntity entity) {
        this.id = entity.getId();
        this.taskId = entity.getTaskId();
        this.title = entity.getTitle();
        this.assignedTo = entity.getAssignedTo();
        this.dueDate = entity.getDueDate();
        if(entity.getType() != null) {
            this.type = new TaskTypeDto(entity.getType());
        }
        if(entity.getStatus() != null) {
            this.status = new TaskStatusDto(entity.getStatus());
        }
        if(entity.getPriority() != null) {
            this.priority = new TaskPriorityDto(entity.getPriority());
        }
    }
}
