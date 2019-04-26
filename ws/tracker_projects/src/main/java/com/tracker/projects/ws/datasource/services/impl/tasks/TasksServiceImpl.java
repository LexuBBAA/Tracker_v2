package com.tracker.projects.ws.datasource.services.impl.tasks;

import com.tracker.projects.ws.datasource.dtos.tasks.previews.TaskPreviewDto;
import com.tracker.projects.ws.datasource.repositories.tasks.TasksRepository;
import com.tracker.projects.ws.datasource.services.TaskPreviewsServicesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasksServiceImpl implements TaskPreviewsServicesCollection.SubtasksService {
    @Autowired
    private TasksRepository repository;

    @Override
    public List<TaskPreviewDto> getSubtasksOf(Integer taskId) {
        return null;
    }
}
