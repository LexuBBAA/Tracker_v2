package com.tracker.projects.ws.datasource.services;

import com.tracker.projects.ws.datasource.dtos.projects.ProjectDto;

public interface ProjectsService {
    ProjectDto getProjectById(Integer projectId);
    ProjectDto createProject(ProjectDto newProject);
    ProjectDto updateProject(ProjectDto projectDto);
}
