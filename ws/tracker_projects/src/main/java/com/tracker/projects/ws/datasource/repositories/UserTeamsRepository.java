package com.tracker.projects.ws.datasource.repositories;

import com.tracker.projects.ws.datasource.entities.UserTeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserTeamsRepository extends JpaRepository<UserTeamEntity, Long> {
    boolean existsByUserIdAndTeamId(String userId, String teamId);
    boolean existsByUserId(String userId);
    boolean existsByTeamId(String teamId);

    List<UserTeamEntity> findAllByUserId(String userId);
    List<UserTeamEntity> findAllByTeamId(String teamId);

    List<UserTeamEntity> findAllByTeamIdAndJoinedDate(String teamId, LocalDate joinedDate);
    List<UserTeamEntity> findAllByTeamIdAndJoinedDateBefore(String teamId, LocalDate joinDate);
    List<UserTeamEntity> findAllByTeamIdAndJoinedDateAfter(String teamId, LocalDate joinDate);
    List<UserTeamEntity> findAllByTeamIdAndJoinedDateAfterAndJoinedDateBefore(String teamId, LocalDate startDate, LocalDate endDate);

    @Transactional
    boolean deleteByUserIdAndTeamId(String userId, String teamId);
    @Transactional
    boolean deleteByTeamId(String teamId);
    @Transactional
    boolean deleteByUserId(String userId);
}
