package com.tracker.logging.ws.datasource.services;

import com.tracker.logging.ws.datasource.dtos.TaskPriorityDto;

import java.util.List;

public interface TaskPriorityService {
    List<TaskPriorityDto> getAll();
    TaskPriorityDto findByValue(String value);
    TaskPriorityDto create(String value);
    boolean delete(String value);
}
