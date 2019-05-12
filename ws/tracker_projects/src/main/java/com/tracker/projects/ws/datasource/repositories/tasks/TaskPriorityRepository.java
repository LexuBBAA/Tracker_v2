package com.tracker.projects.ws.datasource.repositories.tasks;

import com.tracker.projects.ws.datasource.entities.tasks.enums.TaskPriorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TaskPriorityRepository extends JpaRepository<TaskPriorityEntity, Long> {
    TaskPriorityEntity findByPriority(String priority);

    boolean existsByPriority(String priority);

    @Transactional
    boolean deleteByPriority(String priority);
}
