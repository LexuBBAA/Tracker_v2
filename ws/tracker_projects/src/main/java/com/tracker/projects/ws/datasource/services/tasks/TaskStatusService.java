package com.tracker.projects.ws.datasource.services.tasks;

import com.tracker.projects.ws.datasource.dtos.tasks.TaskStatusDto;

import java.util.List;

public interface TaskStatusService {
    TaskStatusDto create(String value);
    List<TaskStatusDto> getAll();
    TaskStatusDto getByValue(String value);
    boolean deleteByValue(String value);
}