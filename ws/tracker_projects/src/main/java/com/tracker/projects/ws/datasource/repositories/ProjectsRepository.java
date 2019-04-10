package com.tracker.projects.ws.datasource.repositories;

import com.tracker.projects.ws.datasource.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends JpaRepository<ProjectEntity, Integer> {
}
