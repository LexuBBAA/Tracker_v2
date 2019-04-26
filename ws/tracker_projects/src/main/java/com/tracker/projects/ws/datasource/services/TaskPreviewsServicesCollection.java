package com.tracker.projects.ws.datasource.services;

import com.tracker.projects.ws.datasource.dtos.tasks.previews.TaskPreviewDto;
import com.tracker.projects.ws.datasource.entities.tasks.enums.TaskStatus;

import java.util.List;

public interface TaskPreviewsServicesCollection {
    interface GeneralTasksService {
        List<TaskPreviewDto> getTasksByProject(Integer projectId);
        List<TaskPreviewDto> getTasksBySprint(Integer sprintId);
        List<TaskPreviewDto> getTasksByEpic(Integer epicId);
        List<TaskPreviewDto> getTasksByStory(Integer storyId);
        List<TaskPreviewDto> getAllAssignedTo(Integer userId);
        List<TaskPreviewDto> getAllCreatedBy(Integer userId);

        boolean deleteTask(Integer taskId);
    }

    interface StoriesService {
        List<TaskPreviewDto> getAllEpicsForProject(Integer projectId);
        List<TaskPreviewDto> getAllStoriesForSprint(Integer sprintId);
    }

    interface IssuesService {
        List<TaskPreviewDto> getProjectIssues(Integer projectId);
        List<TaskPreviewDto> getSprintIssues(Integer sprintId);
        List<TaskPreviewDto> getEpicIssues(Integer epicId);
        List<TaskPreviewDto> getStoryIssues(Integer storyId);

        List<TaskPreviewDto> getAllWithStatusInProject(TaskStatus status, Integer projectId);
        List<TaskPreviewDto> getAllWithStatusInSprint(TaskStatus status, Integer sprintId);
        List<TaskPreviewDto> getIssuesCreatedBy(Integer userId);
        List<TaskPreviewDto> getIssuesAssignedTo(Integer userId);
    }

    interface LinksService {
        List<TaskPreviewDto> getAllInStory(Integer storyId);
        List<TaskPreviewDto> getAllLinkedTo(Integer taskId);
        List<TaskPreviewDto> getAllBlocking(Integer taskId);
        List<TaskPreviewDto> getAllPartOf(Integer taskId);
    }

    interface SubtasksService {
        List<TaskPreviewDto> getSubtasksOf(Integer taskId);
    }
}
