package com.tracker.projects.ws.datasource.dtos.tasks;

import com.tracker.projects.ws.datasource.entities.tasks.enums.TaskStatusEntity;

import java.io.Serializable;

public class TaskStatusDto implements Serializable {
    private static final long serialVersionUID = -7726168850545785060L;

    public Long id;
    public String value;

    public TaskStatusDto() {
    }

    public TaskStatusDto(String value) {
        this.value = value;
    }

    public TaskStatusDto(TaskStatusEntity entity) {
        this.id = entity.getId();
        this.value = entity.getStatus();
    }
}