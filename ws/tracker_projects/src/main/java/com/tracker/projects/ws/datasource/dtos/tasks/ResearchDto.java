package com.tracker.projects.ws.datasource.dtos.tasks;

import com.tracker.projects.ws.datasource.entities.tasks.TaskEntity;
import com.tracker.projects.ws.datasource.entities.tasks.enums.TaskType;

public class ResearchDto extends AbstractTaskDto {
    public Integer partOf;

    public ResearchDto() {
        this.type = TaskType.RESEARCH;
    }

    public ResearchDto(TaskEntity taskEntity) {
        super(taskEntity);
        this.partOf = taskEntity.getPartOf();
        this.type = TaskType.RESEARCH;
    }

    @Override
    public TaskEntity buildEntity() {
        TaskEntity entity = super.buildEntity();
        entity.setPartOf(this.partOf);
        return entity;
    }
}
