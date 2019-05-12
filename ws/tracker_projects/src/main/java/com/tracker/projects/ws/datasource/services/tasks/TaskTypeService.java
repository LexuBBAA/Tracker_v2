package com.tracker.projects.ws.datasource.services.tasks;

import com.tracker.projects.ws.datasource.dtos.tasks.TaskTypeDto;

import java.util.List;

public interface TaskTypeService {
    TaskTypeDto create(String value);
    List<TaskTypeDto> getAll();
    TaskTypeDto getByValue(String value);
    boolean deleteByValue(String value);
}
