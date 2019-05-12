package com.tracker.logging.ws.datasource.dtos;

import com.tracker.logging.ws.datasource.entities.enums.TaskPriorityEntity;

import java.io.Serializable;

public class TaskPriorityDto implements Serializable {
    private static final long serialVersionUID = -5572698538306849099L;

    public Long id;
    public String value;

    public TaskPriorityDto() {
    }

    public TaskPriorityDto(TaskPriorityEntity entity) {
        this.id = entity.getId();
        this.value = entity.getPriority();
    }
}
