package com.tracker.projects.ws.datasource.services.impl.tasks;

import com.tracker.projects.ws.datasource.dtos.tasks.TaskStatusDto;
import com.tracker.projects.ws.datasource.entities.tasks.enums.TaskStatusEntity;
import com.tracker.projects.ws.datasource.repositories.tasks.TaskStatusRepository;
import com.tracker.projects.ws.datasource.services.tasks.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskStatusServiceImpl implements TaskStatusService {
    @Autowired
    private TaskStatusRepository repository;

    @Override
    public TaskStatusDto create(String value) {
        TaskStatusEntity newEntity = new TaskStatusEntity(value);
        TaskStatusEntity savedEntity = repository.save(newEntity);
        if(savedEntity != null) {
            return new TaskStatusDto(savedEntity);
        }
        return null;
    }

    @Override
    public List<TaskStatusDto> getAll() {
        List<TaskStatusDto> dtos = new ArrayList<>();
        List<TaskStatusEntity> storedEntities = repository.findAll();
        for(TaskStatusEntity entity: storedEntities) {
            dtos.add(new TaskStatusDto(entity));
        }
        return dtos;
    }

    @Override
    public TaskStatusDto getByValue(String value) {
        TaskStatusEntity entity = repository.findByStatus(value);
        if(entity != null) {
            return new TaskStatusDto(entity);
        }
        return null;
    }

    @Override
    public boolean deleteByValue(String value) {
        if(repository.existsByStatus(value)) {
            repository.deleteByStatus(value);
            return true;
        }
        return false;
    }
}
