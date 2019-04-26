package com.tracker.projects.ws.datasource.services.impl;

import com.tracker.projects.ws.datasource.dtos.projects.ProjectPreviewDto;
import com.tracker.projects.ws.datasource.entities.projects.ProjectPreviewEntity;
import com.tracker.projects.ws.datasource.repositories.projects.ProjectPreviewRepository;
import com.tracker.projects.ws.datasource.services.ProjectsPreviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectsPreviewServiceImpl implements ProjectsPreviewService {
    @Autowired
    private ProjectPreviewRepository repository;

    @Override
    public List<ProjectPreviewDto> getProjects() {
        return getFromEntities(repository.findAll());
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
}
