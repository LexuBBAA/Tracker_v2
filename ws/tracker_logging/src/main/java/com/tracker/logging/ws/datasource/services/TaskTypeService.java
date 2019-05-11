package com.tracker.logging.ws.datasource.services;

import com.tracker.logging.ws.datasource.dtos.TaskTypeDto;

import java.util.List;

public interface TaskTypeService {
    TaskTypeDto create(String value);
    List<TaskTypeDto> getAll();
    TaskTypeDto getByValue(String value);
    boolean deleteByValue(String value);
}
