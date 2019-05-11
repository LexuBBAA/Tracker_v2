package com.tracker.logging.ws.datasource.dtos;

import com.tracker.logging.ws.datasource.entities.enums.TaskTypeEntity;

import java.io.Serializable;

public class TaskTypeDto implements Serializable {
    private static final long serialVersionUID = 5442815865862739038L;

    public Long id;
    public String value;

    public TaskTypeDto() {
    }

    public TaskTypeDto(TaskTypeEntity entity) {
        this.id = entity.getId();
        this.value = entity.getType();
    }
}
