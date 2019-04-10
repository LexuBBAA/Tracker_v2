package com.tracker.projects.ws.datasource.repositories;

import com.tracker.projects.ws.datasource.entities.SprintEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintsRepository extends JpaRepository<SprintEntity, Integer> {
    List<SprintEntity> findAllByParentProjectId(Integer parentProjectId);
}
