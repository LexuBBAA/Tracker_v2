package com.tracker.logging.ws.datasource.services;

import com.tracker.logging.ws.datasource.dtos.TaskStatusDto;
import com.tracker.logging.ws.datasource.dtos.TaskTypeDto;

import java.util.List;

public interface TaskStatusService {
    TaskStatusDto create(String value);
    List<TaskStatusDto> getAll();
    TaskStatusDto getByValue(String value);
    boolean deleteByValue(String value);
}