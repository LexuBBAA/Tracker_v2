package com.tracker.projects.ws.datasource.repositories;

import com.tracker.projects.ws.datasource.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProjectsRepository extends JpaRepository<ProjectEntity, Long> {
    boolean existsByProjectId(String projectId);
    ProjectEntity findByProjectId(String projectId);

    @Transactional
    void deleteByProjectId(String projectId);
}
