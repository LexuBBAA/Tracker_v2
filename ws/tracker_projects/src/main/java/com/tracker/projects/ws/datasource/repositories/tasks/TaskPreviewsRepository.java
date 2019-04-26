package com.tracker.projects.ws.datasource.repositories.tasks;

import com.tracker.projects.ws.datasource.entities.tasks.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;

@Repository
public interface TaskPreviewsRepository extends JpaRepository<TaskEntity, Integer> {
    ArrayList<TaskEntity> findAllByAssignedTo(Integer userId);
    ArrayList<TaskEntity> findAllByCreatedBy(Integer userId);

    ArrayList<TaskEntity> findAllByEpicId(Integer epicId);
    ArrayList<TaskEntity> findAllBySprintId(Integer sprintId);
    ArrayList<TaskEntity> findAllByProjectId(Integer projectId);
    ArrayList<TaskEntity> findAllByStoryId(Integer storyId);
    ArrayList<TaskEntity> findAllByBlocks(Integer taskId);
    ArrayList<TaskEntity> findAllBySubtaskOf(Integer taskId);
    ArrayList<TaskEntity> findAllByPartOf(Integer taskId);

    ArrayList<TaskEntity> findAllByEpic(boolean isEpic);
    ArrayList<TaskEntity> findAllByPriority(String priority);
    ArrayList<TaskEntity> findAllByStatus(String status);

    ArrayList<TaskEntity> findAllByDueDateIsBefore(Date date);
    ArrayList<TaskEntity> findAllByStartDateIsAfter(Date date);
    ArrayList<TaskEntity> findAllByStartDateIsBetween(Date startDate, Date endDate);
}
