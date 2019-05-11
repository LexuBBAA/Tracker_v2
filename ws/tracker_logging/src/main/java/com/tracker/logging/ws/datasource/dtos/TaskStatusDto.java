package com.tracker.logging.ws.datasource.dtos;

import com.tracker.logging.ws.datasource.entities.enums.TaskStatusEntity;

import java.io.Serializable;

public class TaskStatusDto implements Serializable {
    private static final long serialVersionUID = -7726168850545785060L;

    public Long id;
    public String value;

    public TaskStatusDto() {
    }

    public TaskStatusDto(TaskStatusEntity entity) {
        this.id = entity.getId();
        this.value = entity.getStatus();
    }
}