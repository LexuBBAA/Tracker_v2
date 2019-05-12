package com.tracker.logging.ws.datasource.services.impl;

import com.tracker.logging.ws.datasource.dtos.TaskStatusDto;
import com.tracker.logging.ws.datasource.entities.enums.TaskStatusEntity;
import com.tracker.logging.ws.datasource.repositories.TaskStatusRepository;
import com.tracker.logging.ws.datasource.services.TaskStatusService;
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
        return new TaskStatusDto(savedEntity);
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
        return new TaskStatusDto(repository.findByStatus(value));
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
