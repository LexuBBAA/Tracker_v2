package com.tracker.projects.ws.datasource.services.impl.tasks;

import com.tracker.projects.ws.datasource.dtos.tasks.TaskDto;
import com.tracker.projects.ws.datasource.entities.tasks.TaskEntity;
import com.tracker.projects.ws.datasource.repositories.tasks.TasksRepository;
import com.tracker.projects.ws.datasource.services.tasks.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TasksRepository repository;

    @Override
    public TaskDto createTask(TaskDto newTask) {
        TaskEntity newEntity = new TaskEntity(newTask);
        TaskEntity storedEntity = repository.save(newEntity);
        return new TaskDto(storedEntity);
    }

    @Override
    public TaskDto updateTask(TaskDto task) {
        TaskEntity storedEntity = repository.findByTaskId(task.taskId);
        storedEntity.update(task);
        TaskEntity updatedEntity = repository.save(storedEntity);

        return new TaskDto(updatedEntity);
    }

    @Override
    public TaskDto getTaskDetails(String taskId) {
        return new TaskDto(repository.findByTaskId(taskId));
    }

    @Override
    public boolean deleteById(String taskId) {
        if(repository.existsByTaskId(taskId)) {
            repository.deleteByTaskId(taskId);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(TaskDto taskDto) {
        return deleteById(taskDto.taskId);
    }
}
