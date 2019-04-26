package com.tracker.projects.ws.datasource.dtos.tasks;

import com.tracker.projects.ws.datasource.entities.tasks.TaskEntity;
import com.tracker.projects.ws.datasource.entities.tasks.enums.TaskType;

public class IssueDto extends ResearchDto {
    public Integer blocks;

    public IssueDto() {
        this.type = TaskType.ISSUE;
    }

    public IssueDto(TaskEntity taskEntity) {
        super(taskEntity);
        this.blocks = taskEntity.getBlocks();
        this.type = TaskType.ISSUE;
    }

    @Override
    public TaskEntity buildEntity() {
        TaskEntity entity = super.buildEntity();
        entity.setBlocks(this.blocks);
        return entity;
    }
}
