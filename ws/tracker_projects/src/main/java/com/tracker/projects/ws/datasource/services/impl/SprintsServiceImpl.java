package com.tracker.projects.ws.datasource.services.impl;

import com.tracker.projects.ws.datasource.dtos.sprints.SprintDto;
import com.tracker.projects.ws.datasource.entities.SprintEntity;
import com.tracker.projects.ws.datasource.repositories.sprints.SprintsRepository;
import com.tracker.projects.ws.datasource.services.SprintsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SprintsServiceImpl implements SprintsService {
    @Autowired
    private SprintsRepository repository;

    @Override
    public List<SprintDto> getSprintsForProject(Integer projectId) {
        return getFromEntities(repository.findAllByParentProjectId(projectId));
    }

    @Override
    public SprintDto createSprint(SprintDto newSprint) {
        SprintEntity entity = repository.save(getFromDto(newSprint));
        return getFromEntity(entity);
    }

    @Override
    public SprintDto updateSprint(SprintDto sprint) {
        if(sprint.id == null || !repository.existsById(sprint.id)) {
            return null;
        }

        SprintEntity entity = repository.save(getFromDto(sprint));
        return getFromEntity(entity);
    }

    @Override
    public boolean deleteSprint(Integer sprintId) {
        if(repository.existsById(sprintId)) {
            repository.deleteById(sprintId);
            return true;
        }
        return false;
    }

    private List<SprintDto> getFromEntities(List<SprintEntity> entities) {
        ArrayList<SprintDto> dtos = new ArrayList<>();
        for(SprintEntity entity: entities) {
            dtos.add(getFromEntity(entity));
        }
        return dtos;
    }

    private SprintDto getFromEntity(SprintEntity entity) {
        return new SprintDto(entity);
    }

    private SprintEntity getFromDto(SprintDto dto) {
        return new SprintEntity(dto);
    }
}
