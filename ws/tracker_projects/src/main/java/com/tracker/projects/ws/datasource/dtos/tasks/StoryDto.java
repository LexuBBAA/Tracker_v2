package com.tracker.projects.ws.datasource.dtos.tasks;

import com.tracker.projects.ws.datasource.entities.tasks.TaskEntity;
import com.tracker.projects.ws.datasource.entities.tasks.enums.TaskType;

public class StoryDto extends IssueDto {
    public Integer storyPoints;
    public Boolean isEpic;

    public StoryDto() {
        this.type = TaskType.STORY;
    }

    public StoryDto(TaskEntity taskEntity) {
        super(taskEntity);
        this.storyPoints = taskEntity.getStoryPoints();
        this.isEpic = taskEntity.isEpic();
        this.type = TaskType.STORY;
    }

    @Override
    public TaskEntity buildEntity() {
        TaskEntity entity = super.buildEntity();
        entity.setStoryPoints(this.storyPoints);
        entity.setEpic(this.isEpic);
        return entity;
    }
}
