package com.tracker.projects.ws.datasource.repositories.tasks;

import com.tracker.projects.ws.datasource.entities.tasks.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity, Integer> {
}
