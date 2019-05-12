package com.tracker.logging.ws.datasource.services.impl;

import com.tracker.logging.ws.datasource.dtos.TaskTypeDto;
import com.tracker.logging.ws.datasource.entities.enums.TaskTypeEntity;
import com.tracker.logging.ws.datasource.repositories.TaskTypeRepository;
import com.tracker.logging.ws.datasource.services.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskTypeServiceImpl implements TaskTypeService {
    @Autowired
    private TaskTypeRepository repository;

    @Override
    public TaskTypeDto create(String value) {
        TaskTypeEntity newEntity = new TaskTypeEntity(value);
        TaskTypeEntity storedEntity = repository.save(newEntity);
        return new TaskTypeDto(storedEntity);
    }

    @Override
    public List<TaskTypeDto> getAll() {
        List<TaskTypeDto> dtos = new ArrayList<>();
        List<TaskTypeEntity> storedEntities = repository.findAll();
        for(TaskTypeEntity entity: storedEntities) {
            dtos.add(new TaskTypeDto(entity));
        }
        return dtos;
    }

    @Override
    public TaskTypeDto getByValue(String value) {
        return new TaskTypeDto(repository.findByType(value));
    }

    @Override
    public boolean deleteByValue(String value) {
        if(repository.existsByType(value)) {
            repository.deleteByType(value);
            return true;
        }
        return false;
    }
}
