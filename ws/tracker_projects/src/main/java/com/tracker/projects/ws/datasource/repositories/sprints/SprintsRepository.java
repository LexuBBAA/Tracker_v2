package com.tracker.projects.ws.datasource.repositories.sprints;

import com.tracker.projects.ws.datasource.entities.sprints.SprintEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SprintsRepository extends JpaRepository<SprintEntity, Long> {
    boolean existsBySprintId(String sprintId);
    SprintEntity findBySprintId(String sprintId);

    List<SprintEntity> findAllByTitleLike(String title);
    List<SprintEntity> findAllByProject(String project);
    List<SprintEntity> findAllByCreatedBy(String createdBy);
    List<SprintEntity> findAllByStatusEquals(String status);

    @Transactional
    boolean deleteBySprintId(String sprintId);
}
