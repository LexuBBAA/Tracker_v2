package com.tracker.projects.ws.datasource.repositories.projects;

import com.tracker.projects.ws.datasource.entities.projects.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends JpaRepository<ProjectEntity, Integer> {
}
