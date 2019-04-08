package com.tracker.projects.ws.datasource.services.impl;

import com.tracker.projects.ws.datasource.dtos.ProjectPreviewDto;
import com.tracker.projects.ws.datasource.entities.ProjectPreviewEntity;
import com.tracker.projects.ws.datasource.repositories.ProjectPreviewRepository;
import com.tracker.projects.ws.datasource.services.ProjectsPreviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectsPreviewServiceImpl implements ProjectsPreviewService {
    @Autowired
    private ProjectPreviewRepository repository;

    @Override
    public List<ProjectPreviewDto> getProjects() {
        return getFromEntities(repository.findAll());
    }

    @Override
    public ProjectPreviewDto getProjectById(Integer projectId) {
        Optional<ProjectPreviewEntity> previewEntity = repository.findById(projectId);

        return previewEntity.map(ProjectPreviewDto::new).orElse(null);
    }

    @Override
    public ProjectPreviewDto updateProject(ProjectPreviewDto projectDto) {
        ProjectPreviewEntity savedEntity = repository.save(getFromDto(projectDto));
        return getFromEntity(savedEntity);
    }

    @Override
    public boolean deleteProject(Integer projectId) {
        if(repository.existsById(projectId)) {
            repository.deleteById(projectId);
            return true;
        }

        return false;
    }

    private List<ProjectPreviewDto> getFromEntities(List<ProjectPreviewEntity> entities) {
        List<ProjectPreviewDto> dtos = new ArrayList<>();
        for(ProjectPreviewEntity entity: entities) {
            dtos.add(getFromEntity(entity));
        }
        return dtos;
    }

    private ProjectPreviewDto getFromEntity(ProjectPreviewEntity entity) {
        return new ProjectPreviewDto(entity);
    }

    private ProjectPreviewEntity getFromDto(ProjectPreviewDto dto) {
        return new ProjectPreviewEntity(dto);
    }
}
