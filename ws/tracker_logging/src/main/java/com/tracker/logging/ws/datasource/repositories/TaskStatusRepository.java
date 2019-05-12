package com.tracker.logging.ws.datasource.repositories;

import com.tracker.logging.ws.datasource.entities.enums.TaskStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatusEntity, Long> {
    TaskStatusEntity findByStatus(String status);

    boolean existsByStatus(String status);

    @Transactional
    boolean deleteByStatus(String status);
}
