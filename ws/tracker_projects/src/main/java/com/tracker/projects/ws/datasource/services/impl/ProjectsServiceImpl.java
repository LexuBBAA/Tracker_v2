package com.tracker.projects.ws.datasource.services.impl;

import com.tracker.projects.ws.datasource.dtos.projects.ProjectDto;
import com.tracker.projects.ws.datasource.entities.projects.ProjectEntity;
import com.tracker.projects.ws.datasource.repositories.projects.ProjectsRepository;
import com.tracker.projects.ws.datasource.services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectsServiceImpl implements ProjectsService {
    @Autowired
    private ProjectsRepository repository;

    @Override
    public ProjectDto getProjectById(Integer projectId) {
        Optional<ProjectEntity> previewEntity = repository.findById(projectId);
        return previewEntity.map(ProjectDto::new).orElse(null);
    }

    @Override
    public ProjectDto createProject(ProjectDto newProject) {
        return getFromEntity(repository.save(getFromDto(newProject)));
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto) {
        if(projectDto.id == null || !repository.existsById(projectDto.id)) {
            return null;
        }

        return getFromEntity(repository.save(getFromDto(projectDto)));
    }

    private ProjectEntity getFromDto(ProjectDto dto) {
        return new ProjectEntity(dto);
    }

    private ProjectDto getFromEntity(ProjectEntity entity) {
        return new ProjectDto(entity);
    }
}
