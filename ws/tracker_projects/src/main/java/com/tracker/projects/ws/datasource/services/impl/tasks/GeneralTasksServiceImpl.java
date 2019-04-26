package com.tracker.projects.ws.datasource.services.impl.tasks;

import com.tracker.projects.ws.datasource.dtos.tasks.previews.TaskPreviewDto;
import com.tracker.projects.ws.datasource.entities.tasks.TaskEntity;
import com.tracker.projects.ws.datasource.repositories.tasks.TaskPreviewsRepository;
import com.tracker.projects.ws.datasource.services.TaskPreviewsServicesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeneralTasksServiceImpl implements TaskPreviewsServicesCollection.GeneralTasksService {
    @Autowired
    private TaskPreviewsRepository repository;

    @Override
    public List<TaskPreviewDto> getTasksByProject(Integer projectId) {
        List<TaskEntity> taskPreviewEntities = repository.findAllByProjectId(projectId);

        return getFromEntities(taskPreviewEntities);
    }

    @Override
    public List<TaskPreviewDto> getTasksBySprint(Integer sprintId) {
        List<TaskEntity> taskPreviewEntities = repository.findAllBySprintId(sprintId);

        return getFromEntities(taskPreviewEntities);
    }

    @Override
    public List<TaskPreviewDto> getTasksByEpic(Integer epicId) {
        List<TaskEntity> taskPreviewEntities = repository.findAllByEpicId(epicId);

        return getFromEntities(taskPreviewEntities);
    }

    @Override
    public List<TaskPreviewDto> getTasksByStory(Integer storyId) {
        List<TaskEntity> taskPreviewEntities = repository.findAllByStoryId(storyId);

        return getFromEntities(taskPreviewEntities);
    }

    @Override
    public List<TaskPreviewDto> getAllAssignedTo(Integer userId) {
        List<TaskEntity> taskPreviewEntities = repository.findAllByAssignedTo(userId);

        return getFromEntities(taskPreviewEntities);
    }

    @Override
    public List<TaskPreviewDto> getAllCreatedBy(Integer userId) {
        List<TaskEntity> taskPreviewEntities = repository.findAllByCreatedBy(userId);

        return getFromEntities(taskPreviewEntities);
    }

    @Override
    public boolean deleteTask(Integer taskId) {
        if(repository.existsById(taskId)) {
            repository.deleteById(taskId);
            return true;
        }

        return false;
    }

    private List<TaskPreviewDto> getFromEntities(List<TaskEntity> entities) {
        List<TaskPreviewDto> dtos = new ArrayList<>();
        for(TaskEntity entity: entities) {
            dtos.add(getFromEntity(entity));
        }
        return dtos;
    }

    private TaskPreviewDto getFromEntity(TaskEntity entity) {
        return new TaskPreviewDto(entity);
    }
}
