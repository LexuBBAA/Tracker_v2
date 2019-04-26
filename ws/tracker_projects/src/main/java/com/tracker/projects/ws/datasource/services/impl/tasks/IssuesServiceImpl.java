package com.tracker.projects.ws.datasource.services.impl.tasks;

import com.tracker.projects.ws.datasource.dtos.tasks.previews.TaskPreviewDto;
import com.tracker.projects.ws.datasource.entities.tasks.enums.TaskStatus;
import com.tracker.projects.ws.datasource.repositories.tasks.TasksRepository;
import com.tracker.projects.ws.datasource.services.TaskPreviewsServicesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssuesServiceImpl implements TaskPreviewsServicesCollection.IssuesService {
    @Autowired
    private TasksRepository repository;

    @Override
    public List<TaskPreviewDto> getProjectIssues(Integer projectId) {
        return null;
    }

    @Override
    public List<TaskPreviewDto> getSprintIssues(Integer sprintId) {
        return null;
    }

    @Override
    public List<TaskPreviewDto> getEpicIssues(Integer epicId) {
        return null;
    }

    @Override
    public List<TaskPreviewDto> getStoryIssues(Integer storyId) {
        return null;
    }

    @Override
    public List<TaskPreviewDto> getAllWithStatusInProject(TaskStatus status, Integer projectId) {
        return null;
    }

    @Override
    public List<TaskPreviewDto> getAllWithStatusInSprint(TaskStatus status, Integer sprintId) {
        return null;
    }

    @Override
    public List<TaskPreviewDto> getIssuesCreatedBy(Integer userId) {
        return null;
    }

    @Override
    public List<TaskPreviewDto> getIssuesAssignedTo(Integer userId) {
        return null;
    }
}
