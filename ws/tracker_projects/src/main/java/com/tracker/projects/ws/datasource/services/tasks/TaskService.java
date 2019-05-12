package com.tracker.projects.ws.datasource.services.tasks;

import com.tracker.projects.ws.datasource.dtos.tasks.TaskDto;

public interface TaskService {
    TaskDto createTask(TaskDto newTask);
    TaskDto updateTask(TaskDto task);
    TaskDto getTaskDetails(String taskId);

    boolean deleteById(String taskId);
    boolean delete(TaskDto taskDto);
}
