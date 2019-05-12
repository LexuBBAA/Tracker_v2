package com.tracker.logging.ws.datasource.services;

import com.tracker.logging.ws.datasource.dtos.TaskPreviewDto;

import java.util.List;

public interface TaskPreviewService {
    List<TaskPreviewDto> findEpicsForProject(String projectId);
    List<TaskPreviewDto> findForProject(String projectId);
    List<TaskPreviewDto> findForSprint(String sprintId);

    List<TaskPreviewDto> findTasksWithParent(String parentId);
    List<TaskPreviewDto> findTasksPartOF(String partOfId);
    List<TaskPreviewDto> findTasksForEpic(String epicId);
    List<TaskPreviewDto> findSubtasksOf(String taskId);
    List<TaskPreviewDto> findTasksBlocking(String taskId);
}
