package com.tracker.projects.ws.datasource.services.sprints;

import com.tracker.projects.ws.datasource.dtos.sprints.SprintDto;
import com.tracker.projects.ws.datasource.dtos.sprints.SprintPreviewDto;

import java.util.List;

public interface SprintsService {
    SprintDto create(SprintDto newSprint);
    SprintDto updateSprint(SprintDto sprint);

    SprintDto getSprintDetails(String sprintId);
    List<SprintPreviewDto> getSprintsForProject(String projectId);
    List<SprintPreviewDto> getSprintsWithStatus(String status);

    boolean deleteSprint(String sprintId);
}
