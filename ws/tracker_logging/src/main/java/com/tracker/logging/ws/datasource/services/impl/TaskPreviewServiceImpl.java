package com.tracker.logging.ws.datasource.services.impl;

import com.tracker.logging.ws.datasource.dtos.TaskPreviewDto;
import com.tracker.logging.ws.datasource.entities.TaskEntity;
import com.tracker.logging.ws.datasource.repositories.TasksRepository;
import com.tracker.logging.ws.datasource.services.TaskPreviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskPreviewServiceImpl implements TaskPreviewService {
    @Autowired
    private TasksRepository repository;

    @Override
    public List<TaskPreviewDto> findEpicsForProject(String projectId) {
        List<TaskPreviewDto> dtos = new ArrayList<>();
        List<TaskEntity> storedEntities = repository.findAllByProject(projectId);
        for(TaskEntity entity: storedEntities) {
            if(entity.isEpic()) {
                dtos.add(new TaskPreviewDto(entity));
            }
        }

        return dtos;
    }

    @Override
    public List<TaskPreviewDto> findForProject(String projectId) {
        List<TaskPreviewDto> dtos = new ArrayList<>();
        List<TaskEntity> storedEntities = repository.findAllByProject(projectId);
        for(TaskEntity entity: storedEntities) {
            dtos.add(new TaskPreviewDto(entity));
        }

        return dtos;
    }

    @Override
    public List<TaskPreviewDto> findForSprint(String sprintId) {
        List<TaskPreviewDto> dtos = new ArrayList<>();
        List<TaskEntity> storedEntities = repository.findAllBySprint(sprintId);
        for(TaskEntity entity: storedEntities) {
            dtos.add(new TaskPreviewDto(entity));
        }

        return dtos;
    }

    @Override
    public List<TaskPreviewDto> findTasksWithParent(String parentId) {
        List<TaskPreviewDto> dtos = new ArrayList<>();
        List<TaskEntity> storedEntities = repository.findAllByParent(parentId);
        for(TaskEntity entity: storedEntities) {
            dtos.add(new TaskPreviewDto(entity));
        }

        return dtos;
    }

    @Override
    public List<TaskPreviewDto> findTasksPartOF(String partOfId) {
        List<TaskPreviewDto> dtos = new ArrayList<>();
        List<TaskEntity> storedEntities = repository.findAllByPartOf(partOfId);
        for(TaskEntity entity: storedEntities) {
            dtos.add(new TaskPreviewDto(entity));
        }

        return dtos;
    }

    @Override
    public List<TaskPreviewDto> findTasksForEpic(String epicId) {
        List<TaskPreviewDto> dtos = new ArrayList<>();
        List<TaskEntity> storedEntities = repository.findAllByEpic(epicId);
        for(TaskEntity entity: storedEntities) {
            dtos.add(new TaskPreviewDto(entity));
        }

        return dtos;
    }

    @Override
    public List<TaskPreviewDto> findSubtasksOf(String taskId) {
        List<TaskPreviewDto> dtos = new ArrayList<>();
        List<TaskEntity> storedEntities = repository.findAllBySubtaskOf(taskId);
        for(TaskEntity entity: storedEntities) {
            dtos.add(new TaskPreviewDto(entity));
        }

        return dtos;
    }

    @Override
    public List<TaskPreviewDto> findTasksBlocking(String taskId) {
        List<TaskPreviewDto> dtos = new ArrayList<>();
        List<TaskEntity> storedEntities = repository.findAllByBlocks(taskId);
        for(TaskEntity entity: storedEntities) {
            dtos.add(new TaskPreviewDto(entity));
        }

        return dtos;
    }
}
