package com.tracker.projects.ws.datasource.services.impl.sprints;

import com.tracker.projects.ws.datasource.dtos.sprints.SprintDto;
import com.tracker.projects.ws.datasource.entities.sprints.SprintEntity;
import com.tracker.projects.ws.datasource.repositories.sprints.SprintsRepository;
import com.tracker.projects.ws.datasource.services.sprints.SprintsService;
import io.micrometer.core.lang.NonNull;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SprintsServiceImpl implements SprintsService {
    @Autowired
    private SprintsRepository repository;

    @Override
    public SprintDto create(SprintDto newSprint) {
        newSprint.sprintId = generateSprintId();
        SprintEntity newEntity = new SprintEntity(newSprint);
        return new SprintDto(repository.save(newEntity));
    }

    @Override
    public SprintDto updateSprint(SprintDto sprint) {
        SprintEntity storedSprint = repository.findBySprintId(sprint.sprintId);
        if(sprint.title != null && !sprint.title.equals(storedSprint.getTitle())) {
            storedSprint.setTitle(sprint.title);
        }
        storedSprint.setDescription(sprint.description);
        if(sprint.startDate != null && !sprint.startDate.equals(storedSprint.getStartDate())) {
            storedSprint.setStartDate(sprint.startDate);
        }
        if(sprint.endDate != null && !sprint.endDate.equals(storedSprint.getEndDate())) {
            storedSprint.setEndDate(sprint.endDate);
        }
        if(sprint.project != null && !sprint.project.equals(storedSprint.getProject())) {
            storedSprint.setProject(sprint.project);
        }
        if(sprint.status != null && !sprint.status.value.equals(storedSprint.getStatus())) {
            storedSprint.setStatus(sprint.status.value);
        }

        storedSprint = repository.save(storedSprint);
        return new SprintDto(storedSprint);
    }

    @Override
    public List<SprintDto> getSprintsForProject(String projectId) {
        return getFromEntities(repository.findAllByProject(projectId));
    }

    @Override
    public List<SprintDto> getSprintsWithStatus(String status) {
        return getFromEntities(repository.findAllByStatusEquals(status));
    }

    @Override
    public boolean deleteSprint(String sprintId) {
        if(repository.existsBySprintId(sprintId)) {
            return repository.deleteBySprintId(sprintId);
        }
        return false;
    }

    @NonNull
    private String generateSprintId() {
        String generatedId = RandomStringUtils.randomAlphanumeric(50);
        if(repository.existsBySprintId(generatedId)) {
            return generateSprintId();
        }
        return generatedId;
    }

    @NonNull
    private List<SprintDto> getFromEntities(List<SprintEntity> entities) {
        List<SprintDto> dtos = new ArrayList<>();
        for(SprintEntity entity: entities) {
            dtos.add(new SprintDto(entity));
        }
        return dtos;
    }
}
