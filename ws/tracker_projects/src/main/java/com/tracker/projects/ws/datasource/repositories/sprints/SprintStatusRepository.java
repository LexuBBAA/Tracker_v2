package com.tracker.projects.ws.datasource.repositories.sprints;

import com.tracker.projects.ws.datasource.entities.sprints.enums.SprintStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface SprintStatusRepository extends JpaRepository<SprintStatusEntity, Long> {
    boolean existsByStatus(String status);
    SprintStatusEntity findByStatus(String status);

    @Transactional
    void deleteByStatus(String status);
}
