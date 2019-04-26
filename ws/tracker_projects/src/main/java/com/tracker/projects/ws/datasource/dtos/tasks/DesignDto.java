package com.tracker.projects.ws.datasource.dtos.tasks;

import com.tracker.projects.ws.datasource.entities.tasks.TaskEntity;
import com.tracker.projects.ws.datasource.entities.tasks.enums.TaskType;

public class DesignDto extends IssueDto {

    public DesignDto() {
        this.type = TaskType.DESIGN;
    }

    public DesignDto(TaskEntity taskEntity) {
        super(taskEntity);
        this.type = TaskType.DESIGN;
    }
}
