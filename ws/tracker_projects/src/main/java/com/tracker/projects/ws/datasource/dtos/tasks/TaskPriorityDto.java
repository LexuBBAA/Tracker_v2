package com.tracker.projects.ws.datasource.dtos.tasks;

import com.tracker.projects.ws.datasource.entities.tasks.enums.TaskPriorityEntity;

import java.io.Serializable;

public class TaskPriorityDto implements Serializable {
    private static final long serialVersionUID = -5572698538306849099L;

    public Long id;
    public String value;

    public TaskPriorityDto() {
    }

    public TaskPriorityDto(String value) {
        this.value = value;
    }

    public TaskPriorityDto(TaskPriorityEntity entity) {
        this.id = entity.getId();
        this.value = entity.getPriority();
    }
}
