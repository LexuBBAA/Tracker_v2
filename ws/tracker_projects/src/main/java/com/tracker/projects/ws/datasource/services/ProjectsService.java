package com.tracker.projects.ws.datasource.services;

import com.tracker.projects.ws.datasource.dtos.ProjectDto;

import java.util.List;

public interface ProjectsService {
    ProjectDto create(ProjectDto newProject);
    ProjectDto update(ProjectDto project);

    ProjectDto getById(String projectId);
    List<ProjectDto> getAll();

    boolean delete(ProjectDto project);
    boolean deleteById(String projectId);
}
