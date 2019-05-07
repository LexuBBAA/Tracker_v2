package com.tracker.projects.ws.datasource.services.impl;

import com.tracker.projects.ws.datasource.dtos.TeamPreviewDto;
import com.tracker.projects.ws.datasource.entities.TeamEntity;
import com.tracker.projects.ws.datasource.repositories.TeamsRepository;
import com.tracker.projects.ws.datasource.services.TeamsPreviewService;
import io.micrometer.core.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamsPreviewServiceImpl implements TeamsPreviewService {

    @Autowired
    private TeamsRepository repository;

    @Override
    public List<TeamPreviewDto> getTeams() {
        return getFromEntities(repository.findAll());
    }

    @Override
    public List<TeamPreviewDto> getTeamsByName(String name) {
        return getFromEntities(repository.findAllByNameLike(name));
    }

    @Override
    public List<TeamPreviewDto> getTeamsByCreatedById(String createdById) {
        return getFromEntities(repository.findAllByCreatedBy(createdById));
    }

    @NonNull
    private TeamPreviewDto getFromEntity(@NonNull TeamEntity entity) {
        return new TeamPreviewDto(entity);
    }

    @NonNull
    private List<TeamPreviewDto> getFromEntities(@NonNull List<TeamEntity> entities) {
        List<TeamPreviewDto> dtos = new ArrayList<>();
        for(TeamEntity entity: entities) {
            dtos.add(getFromEntity(entity));
        }
        return dtos;
    }
}
