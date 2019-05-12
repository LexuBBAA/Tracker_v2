package com.tracker.logging.ws.datasource.services.impl;

import com.tracker.logging.ws.datasource.dtos.TaskDto;
import com.tracker.logging.ws.datasource.entities.TaskEntity;
import com.tracker.logging.ws.datasource.repositories.TasksRepository;
import com.tracker.logging.ws.datasource.services.TaskLinkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskLinkingServiceImpl implements TaskLinkingService {
    @Autowired
    private TasksRepository repository;

    @Override
    public TaskDto setTaskBlocks(String sourceId, String blocksId) {
        if(!repository.existsByTaskId(sourceId) || !repository.existsByTaskId(blocksId)){
            return null;
        }

        TaskEntity storedEntity = repository.findByTaskId(sourceId);
        storedEntity.setBlocks(blocksId);
        TaskEntity updatedEntity = repository.save(storedEntity);

        return new TaskDto(updatedEntity);
    }

    @Override
    public TaskDto setTaskPartOf(String sourceId, String partOfId) {
        if(!repository.existsByTaskId(sourceId) || !repository.existsByTaskId(partOfId)){
            return null;
        }

        TaskEntity storedEntity = repository.findByTaskId(sourceId);
        storedEntity.setPartOf(partOfId);
        TaskEntity updatedEntity = repository.save(storedEntity);

        return new TaskDto(updatedEntity);
    }

    @Override
    public TaskDto setTaskParent(String sourceId, String parentId) {
        if(!repository.existsByTaskId(sourceId) || !repository.existsByTaskId(parentId)){
            return null;
        }

        TaskEntity storedEntity = repository.findByTaskId(sourceId);
        storedEntity.setParent(parentId);
        TaskEntity updatedEntity = repository.save(storedEntity);

        return new TaskDto(updatedEntity);
    }

    @Override
    public TaskDto setTaskEpic(String sourceId, String epicId) {
        if(!repository.existsByTaskId(sourceId) || !repository.existsByTaskId(epicId)){
            return null;
        }

        TaskEntity storedEntity = repository.findByTaskId(sourceId);
        storedEntity.setEpic(epicId);
        TaskEntity updatedEntity = repository.save(storedEntity);

        return new TaskDto(updatedEntity);
    }

    @Override
    public TaskDto setTaskProject(String sourceId, String projectId) {
        if(!repository.existsByTaskId(sourceId)){
            return null;
        }

        TaskEntity storedEntity = repository.findByTaskId(sourceId);
        storedEntity.setProject(projectId);
        TaskEntity updatedEntity = repository.save(storedEntity);

        return new TaskDto(updatedEntity);
    }

    @Override
    public TaskDto setTaskSprint(String sourceId, String sprintId) {
        if(!repository.existsByTaskId(sourceId)){
            return null;
        }

        TaskEntity storedEntity = repository.findByTaskId(sourceId);
        storedEntity.setSprint(sprintId);
        TaskEntity updatedEntity = repository.save(storedEntity);

        return new TaskDto(updatedEntity);
    }

    @Override
    public TaskDto setSubtaskOf(String sourceId, String taskId) {
        if(!repository.existsByTaskId(sourceId) || !repository.existsByTaskId(taskId)){
            return null;
        }

        TaskEntity storedEntity = repository.findByTaskId(sourceId);
        storedEntity.setSubtaskOf(taskId);
        TaskEntity updatedEntity = repository.save(storedEntity);

        return new TaskDto(updatedEntity);
    }

    @Override
    public TaskDto removeBlocks(TaskDto task) {
        if(task.blocks != null && repository.existsByTaskId(task.blocks.taskId)) {
            TaskEntity storedEntity = repository.findByTaskId(task.taskId);
            storedEntity.setBlocks(null);
            TaskEntity updatedEntity = repository.save(storedEntity);
            task = new TaskDto(updatedEntity);
        }
        return task;
    }

    @Override
    public TaskDto removePartOf(TaskDto task) {
        if(task.partOf != null && repository.existsByTaskId(task.partOf.taskId)) {
            TaskEntity storedEntity = repository.findByTaskId(task.taskId);
            storedEntity.setPartOf(null);
            TaskEntity updatedEntity = repository.save(storedEntity);
            task = new TaskDto(updatedEntity);
        }
        return task;
    }

    @Override
    public TaskDto removeParent(TaskDto task) {
        if(task.project == null) {
            return task;
        }

        if(task.parent != null && repository.existsByTaskId(task.parent.taskId)) {
            TaskEntity storedEntity = repository.findByTaskId(task.taskId);
            storedEntity.setParent(null);
            TaskEntity updatedEntity = repository.save(storedEntity);
            task = new TaskDto(updatedEntity);
        }
        return task;
    }

    @Override
    public TaskDto removeSubtask(TaskDto task) {
        if(task.subtaskOf != null && repository.existsByTaskId(task.subtaskOf.taskId)) {
            TaskEntity storedEntity = repository.findByTaskId(task.taskId);
            storedEntity.setSubtaskOf(null);
            TaskEntity updatedEntity = repository.save(storedEntity);
            task = new TaskDto(updatedEntity);
            return task;
        }

        return task;
    }
}
