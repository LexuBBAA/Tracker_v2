package com.tracker.projects.ws.datasource.repositories;

import com.tracker.projects.ws.datasource.entities.projects.ProjectPreviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectPreviewRepository extends JpaRepository<ProjectPreviewEntity, Integer> {
}
