package com.tracker.logging.ws.datasource.services.impl;

import com.tracker.logging.ws.datasource.dtos.TaskPriorityDto;
import com.tracker.logging.ws.datasource.entities.enums.TaskPriorityEntity;
import com.tracker.logging.ws.datasource.repositories.TaskPriorityRepository;
import com.tracker.logging.ws.datasource.services.TaskPriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskPriorityServiceImpl implements TaskPriorityService {
    @Autowired
    private TaskPriorityRepository repository;

    @Override
    public List<TaskPriorityDto> getAll() {
        List<TaskPriorityDto> dtos = new ArrayList<>();
        List<TaskPriorityEntity> storedEntities = repository.findAll();
        for(TaskPriorityEntity entity: storedEntities) {
            dtos.add(new TaskPriorityDto(entity));
        }
        return dtos;
    }

    @Override
    public TaskPriorityDto findByValue(String value) {
        return new TaskPriorityDto(repository.findByPriority(value));
    }

    @Override
    public TaskPriorityDto create(String value) {
        TaskPriorityEntity newEntity = new TaskPriorityEntity(value);
        return new TaskPriorityDto(repository.save(newEntity));
    }

    @Override
    public boolean delete(String value) {
        if(repository.existsByPriority(value)) {
            repository.deleteByPriority(value);
            return true;
        }
        return false;
    }
}
