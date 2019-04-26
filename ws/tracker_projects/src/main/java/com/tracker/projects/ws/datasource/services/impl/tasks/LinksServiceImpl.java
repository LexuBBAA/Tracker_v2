package com.tracker.projects.ws.datasource.services.impl.tasks;

import com.tracker.projects.ws.datasource.dtos.tasks.previews.TaskPreviewDto;
import com.tracker.projects.ws.datasource.repositories.tasks.TasksRepository;
import com.tracker.projects.ws.datasource.services.TaskPreviewsServicesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinksServiceImpl implements TaskPreviewsServicesCollection.LinksService {
    @Autowired
    private TasksRepository repository;

    @Override
    public List<TaskPreviewDto> getAllInStory(Integer storyId) {
        return null;
    }

    @Override
    public List<TaskPreviewDto> getAllLinkedTo(Integer taskId) {
        return null;
    }

    @Override
    public List<TaskPreviewDto> getAllBlocking(Integer taskId) {
        return null;
    }

    @Override
    public List<TaskPreviewDto> getAllPartOf(Integer taskId) {
        return null;
    }
}
