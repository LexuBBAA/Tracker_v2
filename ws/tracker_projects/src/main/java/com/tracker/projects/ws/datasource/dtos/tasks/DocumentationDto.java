package com.tracker.projects.ws.datasource.dtos.tasks;

import com.tracker.projects.ws.datasource.entities.tasks.TaskEntity;
import com.tracker.projects.ws.datasource.entities.tasks.enums.TaskType;

public class DocumentationDto extends IssueDto {

    public DocumentationDto() {
        this.type = TaskType.DOCUMENTATION;
    }

    public DocumentationDto(TaskEntity taskEntity) {
        super(taskEntity);
        this.type = TaskType.DOCUMENTATION;
    }
}
