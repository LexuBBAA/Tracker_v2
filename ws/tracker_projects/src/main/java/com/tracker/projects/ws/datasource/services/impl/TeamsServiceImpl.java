package com.tracker.projects.ws.datasource.services.impl;

import com.tracker.projects.ws.datasource.dtos.TeamDto;
import com.tracker.projects.ws.datasource.entities.TeamEntity;
import com.tracker.projects.ws.datasource.repositories.TeamsRepository;
import com.tracker.projects.ws.datasource.services.TeamsService;
import io.micrometer.core.lang.NonNull;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamsServiceImpl implements TeamsService {

    @Autowired
    private TeamsRepository repository;

    @Override
    public TeamDto getTeamById(String teamId) {
        return getFromEntity(repository.findByTeamId(teamId));
    }

    @Override
    public TeamDto updateTeam(TeamDto updatedTeam) {
        if(!repository.existsByTeamId(updatedTeam.teamId)) {
            return null;
        }

        TeamEntity storedEntity = repository.findByTeamId(updatedTeam.teamId);
        storedEntity.setName(updatedTeam.name);
        storedEntity.setDescription(updatedTeam.description);

        TeamEntity updatedEntity = repository.save(storedEntity);
        return getFromEntity(updatedEntity);
    }

    @Override
    public boolean deleteTeamById(String teamId) {
        if(repository.existsByTeamId(teamId)) {
            repository.deleteByTeamId(teamId);
            return true;
        }
        return false;
    }

    @Override
    public TeamDto createTeam(TeamDto teamDto) {
        teamDto.teamId = generateTeamId();
        TeamEntity storedEntity = repository.save(getFromDto(teamDto));
        return getFromEntity(storedEntity);
    }


    @NonNull
    private TeamDto getFromEntity(@NonNull TeamEntity entity) {
        return new TeamDto(entity);
    }

    @NonNull
    private TeamEntity getFromDto(@NonNull TeamDto dto) {
        return new TeamEntity(dto);
    }

    @NonNull
    private String generateTeamId() {
        String generatedId = RandomStringUtils.randomAlphanumeric(50);
        if(repository.existsByTeamId(generatedId)) {
            return generateTeamId();
        }

        return generatedId;
    }
}
