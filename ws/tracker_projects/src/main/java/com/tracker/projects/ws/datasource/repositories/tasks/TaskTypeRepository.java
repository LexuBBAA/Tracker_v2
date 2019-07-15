package com.tracker.projects.ws.datasource.repositories.tasks;

import com.tracker.projects.ws.datasource.entities.tasks.enums.TaskTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TaskTypeRepository extends JpaRepository<TaskTypeEntity, Long> {
    TaskTypeEntity findByType(String type);
    boolean existsByType(String type);

    @Transactional
    void deleteByType(String type);
}
