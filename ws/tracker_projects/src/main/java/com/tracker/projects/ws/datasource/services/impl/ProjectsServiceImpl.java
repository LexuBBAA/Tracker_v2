package com.tracker.projects.ws.datasource.services.impl;

import com.tracker.projects.ws.datasource.dtos.ProjectDto;
import com.tracker.projects.ws.datasource.dtos.sprints.SprintPreviewDto;
import com.tracker.projects.ws.datasource.entities.ProjectEntity;
import com.tracker.projects.ws.datasource.entities.sprints.SprintEntity;
import com.tracker.projects.ws.datasource.repositories.ProjectsRepository;
import com.tracker.projects.ws.datasource.repositories.sprints.SprintsRepository;
import com.tracker.projects.ws.datasource.services.ProjectsService;
import io.micrometer.core.lang.NonNull;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectsServiceImpl implements ProjectsService {
    @Autowired
    private ProjectsRepository repository;

    @Autowired
    private SprintsRepository sprintsRepository;

    @Override
    public ProjectDto create(ProjectDto newProject) {
        newProject.projectId = generateProjectId();
        ProjectEntity newEntity = new ProjectEntity(newProject);
        newEntity = repository.save(newEntity);
        return new ProjectDto(newEntity);
    }

    @Override
    public ProjectDto update(ProjectDto project) {
        if(!repository.existsByProjectId(project.projectId)) {
            return null;
        }

        ProjectEntity storedEntity = repository.findByProjectId(project.projectId);
        if(project.title != null && !project.title.equals(storedEntity.getTitle())) {
            storedEntity.setTitle(project.title);
        }
        storedEntity.setDescription(project.description);
        if(project.activeSprint != null && !project.activeSprint.sprintId.equals(storedEntity.getActiveSprint())) {
            storedEntity.setActiveSprint(project.activeSprint.sprintId);
        }
        if(project.assignedTeam != null && !project.assignedTeam.equals(storedEntity.getAssignedTeam())) {
            storedEntity.setAssignedTeam(project.assignedTeam);
        }

        storedEntity = repository.save(storedEntity);
        project = new ProjectDto(storedEntity);

        SprintEntity sprintEntity = sprintsRepository.findBySprintId(storedEntity.getActiveSprint());
        if(sprintEntity != null) {
            project.activeSprint = new SprintPreviewDto(sprintEntity);
        }
        return project;
    }

    @Override
    public ProjectDto getById(String projectId) {
        ProjectEntity storedProject = repository.findByProjectId(projectId);
        ProjectDto projectDto = new ProjectDto(storedProject);

        SprintEntity sprintEntity = sprintsRepository.findBySprintId(storedProject.getActiveSprint());
        if(sprintEntity != null) {
            projectDto.activeSprint = new SprintPreviewDto(sprintEntity);
        }
        return projectDto;
    }

    @Override
    public List<ProjectDto> getAll() {
        List<ProjectEntity> storedEntities = repository.findAll();
        List<ProjectDto> dtos = new ArrayList<>();
        for(ProjectEntity entity: storedEntities) {
            ProjectDto newDto = new ProjectDto(entity);
            SprintEntity sprintEntity = sprintsRepository.findBySprintId(entity.getActiveSprint());
            if(sprintEntity != null) {
                newDto.activeSprint = new SprintPreviewDto(sprintEntity);
            }
            dtos.add(newDto);
        }
        return dtos;
    }

    @Override
    public boolean delete(ProjectDto project) {
        return deleteById(project.projectId);
    }

    @Override
    public boolean deleteById(String projectId) {
        if(repository.existsByProjectId(projectId)) {
            sprintsRepository.deleteAllByProject(projectId);
            repository.deleteByProjectId(projectId);
            return true;
        }
        return false;
    }

    @NonNull
    private String generateProjectId() {
        String generatedId = RandomStringUtils.randomAlphanumeric(50);
        if(repository.existsByProjectId(generatedId)) {
            return generateProjectId();
        }
        return generatedId;
    }
}
