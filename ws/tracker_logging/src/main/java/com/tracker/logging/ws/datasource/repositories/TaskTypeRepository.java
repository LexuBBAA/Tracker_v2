package com.tracker.logging.ws.datasource.repositories;

import com.tracker.logging.ws.datasource.entities.enums.TaskTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TaskTypeRepository extends JpaRepository<TaskTypeEntity, Long> {
    TaskTypeEntity findByType(String type);
    boolean existsByType(String type);

    @Transactional
    boolean deleteByType(String type);
}
