package com.tracker.projects.ws.datasource.dtos.tasks;

import com.tracker.projects.ws.datasource.entities.tasks.enums.TaskTypeEntity;

import java.io.Serializable;

public class TaskTypeDto implements Serializable {
    private static final long serialVersionUID = 5442815865862739038L;

    public Long id;
    public String value;

    public TaskTypeDto() {
    }

    public TaskTypeDto(String value) {
        this.value = value;
    }

    public TaskTypeDto(TaskTypeEntity entity) {
        this.id = entity.getId();
        this.value = entity.getType();
    }
}
