package com.tracker.projects.ws.datasource.dtos.sprints;

import com.tracker.projects.ws.datasource.entities.sprints.enums.SprintStatusEntity;

import java.io.Serializable;

public class SprintStatusDto implements Serializable {
    private static final long serialVersionUID = 7176229544803080507L;

    public Long id;
    public String value;

    public SprintStatusDto(String value) {
        this.value = value;
    }

    public SprintStatusDto(SprintStatusEntity entity) {
        this.id = entity.getId();
        this.value = entity.getStatus();
    }
}
