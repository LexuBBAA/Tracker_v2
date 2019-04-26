package com.tracker.projects.ws.datasource.dtos.tasks;

import com.tracker.projects.ws.datasource.entities.tasks.TaskEntity;
import com.tracker.projects.ws.datasource.entities.tasks.enums.TaskType;

public class TaskDto extends IssueDto {
    public Integer subtaskOf;

    public TaskDto() {
        this.type = TaskType.TASK;
    }

    public TaskDto(TaskEntity taskEntity) {
        super(taskEntity);
        this.subtaskOf = taskEntity.getSubtaskOf();
        this.type = taskEntity.getSubtaskOf() != null ? TaskType.SUBTASK: TaskType.TASK;
    }

    @Override
    public TaskEntity buildEntity() {
        TaskEntity entity = super.buildEntity();
        entity.setSubtaskOf(this.subtaskOf);
        return entity;
    }
}
