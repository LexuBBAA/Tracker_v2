package com.tracker.logging.ws.datasource.services;

import com.tracker.logging.ws.datasource.dtos.TaskDto;

public interface TaskService {
    TaskDto createTask(TaskDto newTask);
    TaskDto updateTask(TaskDto task);
    TaskDto getTaskDetails(String taskId);

    boolean deleteById(String taskId);
    boolean delete(TaskDto taskDto);
}
