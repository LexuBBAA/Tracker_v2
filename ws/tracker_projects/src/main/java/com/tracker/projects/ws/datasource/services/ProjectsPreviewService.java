package com.tracker.projects.ws.datasource.services;

import com.tracker.projects.ws.datasource.dtos.projects.ProjectPreviewDto;

import java.util.List;

public interface ProjectsPreviewService {
    List<ProjectPreviewDto> getProjects();
    boolean deleteProject(Integer projectId);
}
