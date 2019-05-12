package com.tracker.logging.ws.datasource.repositories;

import com.tracker.logging.ws.datasource.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity, Long> {
    TaskEntity findByTaskId(String taskId);

    List<TaskEntity> findAllByProject(String project);
    List<TaskEntity> findAllBySprint(String sprint);
    List<TaskEntity> findAllByEpicIsEqualToTrue();

    List<TaskEntity> findAllByParent(String parent);
    List<TaskEntity> findAllByEpic(String epic);
    List<TaskEntity> findAllByPartOf(String partOf);
    List<TaskEntity> findAllBySubtaskOf(String subtaskOf);
    List<TaskEntity> findAllByBlocks(String blocks);

    boolean existsByTaskId(String taskId);

    @Transactional
    boolean deleteByTaskId(String taskId);
}
