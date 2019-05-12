package com.tracker.logging.ws.datasource.dtos;

import com.tracker.logging.ws.datasource.entities.TaskEntity;

import java.io.Serializable;
import java.time.LocalDate;

public class TaskPreviewDto implements Serializable {
    private static final long serialVersionUID = -1279980678059418089L;

    public Long id;
    public String taskId;
    public String title;
    public String assignedTo;
    public LocalDate dueDate;
    public TaskTypeDto type = new TaskTypeDto();
    public TaskStatusDto status = new TaskStatusDto();
    public TaskPriorityDto priority = new TaskPriorityDto();

    public TaskPreviewDto() {
    }

    public TaskPreviewDto(TaskEntity entity) {
        this.id = entity.getId();
        this.taskId = entity.getTaskId();
        this.title = entity.getTitle();
        this.assignedTo = entity.getAssignedTo();
        this.dueDate = entity.getDueDate();
        this.type.value = entity.getType();
        this.status.value = entity.getStatus();
        this.priority.value = entity.getPriority();
    }
}
