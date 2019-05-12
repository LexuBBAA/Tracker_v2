package com.tracker.projects.ws.datasource.services.impl.sprints;

import com.tracker.projects.ws.datasource.dtos.sprints.SprintStatusDto;
import com.tracker.projects.ws.datasource.entities.sprints.enums.SprintStatusEntity;
import com.tracker.projects.ws.datasource.repositories.sprints.SprintStatusRepository;
import com.tracker.projects.ws.datasource.services.sprints.SprintStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SprintStatusServiceImpl implements SprintStatusService {
    @Autowired
    private SprintStatusRepository repository;

    @Override
    public SprintStatusDto getByValue(String value) {
        if(repository.existsByStatus(value)) {
            return new SprintStatusDto(repository.findByStatus(value));
        }
        return null;
    }

    @Override
    public List<SprintStatusDto> getAll() {
        List<SprintStatusDto> dtos = new ArrayList<>();
        List<SprintStatusEntity> entities = repository.findAll();
        for(SprintStatusEntity entity: entities) {
            dtos.add(new SprintStatusDto(entity));
        }
        return dtos;
    }

    @Override
    public boolean deleteByValue(String value) {
        if(repository.existsByStatus(value)) {
            repository.deleteByStatus(value);
            return true;
        }
        return false;
    }
}
