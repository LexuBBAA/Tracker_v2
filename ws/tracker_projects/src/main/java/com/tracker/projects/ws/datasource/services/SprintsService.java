package com.tracker.projects.ws.datasource.services;

import com.tracker.projects.ws.datasource.dtos.sprints.SprintDto;

import java.util.List;

public interface SprintsService {
    List<SprintDto> getSprintsForProject(Integer projectId);
    SprintDto createSprint(SprintDto newSprint);
    SprintDto updateSprint(SprintDto sprint);
    boolean deleteSprint(Integer sprintId);
}
