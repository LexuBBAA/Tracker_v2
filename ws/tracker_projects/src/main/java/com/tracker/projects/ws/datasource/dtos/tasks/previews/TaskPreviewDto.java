package com.tracker.projects.ws.datasource.dtos.tasks.previews;

import com.tracker.projects.ws.datasource.dtos.tasks.AbstractTaskDto;
import com.tracker.projects.ws.datasource.entities.tasks.TaskEntity;
import com.tracker.projects.ws.datasource.entities.tasks.enums.TaskPriority;
import com.tracker.projects.ws.datasource.entities.tasks.enums.TaskType;
import com.tracker.projects.ws.datasource.responses.UserPreviewResponse;

import java.io.Serializable;

public class TaskPreviewDto implements Serializable {
    public Integer id;
    public String title;
    public TaskPriority priority;
    public TaskType type;
    public TaskUserPreviewDto assignedUser;
    public Double totalLoggedRatio;

    public TaskPreviewDto(AbstractTaskDto taskDto, UserPreviewResponse userPreview) {
        this.id = taskDto.id;
        this.title = taskDto.title;
        this.priority = taskDto.priority;
        this.type = taskDto.type;
        this.assignedUser = new TaskUserPreviewDto(userPreview);
        this.totalLoggedRatio = taskDto.loggedWork / taskDto.estimate;
    }

    public TaskPreviewDto(TaskEntity entity) {
        this.id = entity.getTaskId();
        this.title = entity.getTitle();
        this.priority = TaskPriority.valueOf(entity.getPriority());
        this.type = TaskType.valueOf(entity.getType());
        if(entity.getAssignedTo() != null) {
            assignedUser = new TaskUserPreviewDto(entity.getAssignedTo());
        }
        if(entity.getLoggedWork() == null || entity.getEstimate() == null || entity.getEstimate() == 0) {
            this.totalLoggedRatio = 0.0;
        } else {
            this.totalLoggedRatio = entity.getLoggedWork() / entity.getEstimate();
        }
    }
}
