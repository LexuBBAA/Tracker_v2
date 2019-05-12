package com.tracker.projects.ws.datasource.services.tasks;

import com.tracker.projects.ws.datasource.dtos.tasks.TaskPriorityDto;

import java.util.List;

public interface TaskPriorityService {
    List<TaskPriorityDto> getAll();
    TaskPriorityDto findByValue(String value);
    TaskPriorityDto create(String value);
    boolean delete(String value);
}
