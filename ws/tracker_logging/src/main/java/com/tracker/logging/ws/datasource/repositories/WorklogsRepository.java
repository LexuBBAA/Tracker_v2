package com.tracker.logging.ws.datasource.repositories;

import com.tracker.logging.ws.datasource.entities.WorklogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface WorklogsRepository extends JpaRepository<WorklogEntity, Long> {
    boolean existsByWorklogId(String worklogId);
    WorklogEntity findByWorklogId(String worklogId);
    List<WorklogEntity> findAllByRelatesTo(String relatesTo);

    @Transactional
    void deleteAllByRelatesTo(String relatesTo);
    @Transactional
    void deleteByWorklogId(String worklogId);
}
