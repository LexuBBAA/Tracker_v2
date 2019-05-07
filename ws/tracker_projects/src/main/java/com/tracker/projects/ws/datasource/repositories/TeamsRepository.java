package com.tracker.projects.ws.datasource.repositories;

import com.tracker.projects.ws.datasource.entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TeamsRepository extends JpaRepository<TeamEntity, Integer> {

    boolean existsByTeamId(String teamId);
    TeamEntity findByTeamId(String teamId);

    @Transactional
    void deleteByTeamId(String teamId);

    List<TeamEntity> findAllByNameLike(String name);
    List<TeamEntity> findAllByCreatedBy(String createdBy);
}
