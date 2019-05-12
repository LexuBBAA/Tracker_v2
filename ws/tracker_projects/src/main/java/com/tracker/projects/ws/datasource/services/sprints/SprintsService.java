package com.tracker.projects.ws.datasource.services.sprints;

import com.tracker.projects.ws.datasource.dtos.sprints.SprintDto;

import java.util.List;

public interface SprintsService {
    SprintDto create(SprintDto newSprint);
    SprintDto updateSprint(SprintDto sprint);

    List<SprintDto> getSprintsForProject(String projectId);
    List<SprintDto> getSprintsWithStatus(String status);

    boolean deleteSprint(String sprintId);
}
