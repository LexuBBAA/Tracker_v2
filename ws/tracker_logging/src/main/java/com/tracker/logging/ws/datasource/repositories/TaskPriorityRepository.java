package com.tracker.logging.ws.datasource.repositories;

import com.tracker.logging.ws.datasource.entities.enums.TaskPriorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TaskPriorityRepository extends JpaRepository<TaskPriorityEntity, Long> {
    TaskPriorityEntity findByPriority(String priority);

    boolean existsByPriority(String priority);

    @Transactional
    boolean deleteByPriority(String priority);
}
