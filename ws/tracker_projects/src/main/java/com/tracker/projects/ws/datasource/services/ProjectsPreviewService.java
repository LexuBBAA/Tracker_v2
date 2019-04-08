package com.tracker.projects.ws.datasource.services;

import com.tracker.projects.ws.datasource.dtos.ProjectPreviewDto;

import java.util.List;

public interface ProjectsPreviewService {
    List<ProjectPreviewDto> getProjects();
    ProjectPreviewDto getProjectById(Integer projectId);
    ProjectPreviewDto updateProject(ProjectPreviewDto projectDto);
    boolean deleteProject(Integer projectId);
}
