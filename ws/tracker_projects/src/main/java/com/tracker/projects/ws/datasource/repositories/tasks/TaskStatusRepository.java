package com.tracker.projects.ws.datasource.repositories.tasks;

import com.tracker.projects.ws.datasource.entities.tasks.enums.TaskStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatusEntity, Long> {
    TaskStatusEntity findByStatus(String status);

    boolean existsByStatus(String status);

    @Transactional
    boolean deleteByStatus(String status);
}
