package com.tracker.logging.ws.datasource.services;

import com.tracker.logging.ws.datasource.dtos.TaskDto;

public interface TaskLinkingService {
    TaskDto setTaskBlocks(String sourceId, String blocksId);
    TaskDto setTaskPartOf(String sourceId, String partOfId);
    TaskDto setTaskParent(String sourceId, String parentId);
    TaskDto setTaskEpic(String sourceId, String epicId);
    TaskDto setTaskProject(String sourceId, String projectId);
    TaskDto setTaskSprint(String sourceId, String sprintId);
    TaskDto setSubtaskOf(String sourceId, String taskId);

    TaskDto removeBlocks(TaskDto task);
    TaskDto removePartOf(TaskDto task);
    TaskDto removeParent(TaskDto task);
    TaskDto removeSubtask(TaskDto task);
}
