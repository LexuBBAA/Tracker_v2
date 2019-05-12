package com.tracker.logging.ws.datasource.dtos;

import com.tracker.logging.ws.datasource.entities.WorklogEntity;

import java.time.LocalDateTime;

public class WorklogDto {
    public Long id;
    public String worklogId;
    public Double value;
    public String comment;
    public String relatesTo;
    public LocalDateTime createdAt;
    public LocalDateTime lastModifiedAt;
    public String createdBy;
    public String lastModifiedBy;

    public WorklogDto() {
    }

    public WorklogDto(WorklogEntity entity) {
        this.id = entity.getId();
        this.worklogId = entity.getWorklogId();
        this.value = entity.getValue();
        this.comment = entity.getComment();
        this.relatesTo = entity.getRelatesTo();
        this.createdAt = entity.getCreatedAt();
        this.lastModifiedAt = entity.getLastModifiedAt();
        this.createdBy = entity.getCreatedBy();
        this.lastModifiedBy = entity.getLastModifiedBy();
    }
}
