package com.tracker.users.ws.datasource.repositories;

import com.tracker.users.ws.datasource.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findByTeamId(int teamId);
    List<UserEntity> findByTeamIdIn(Set<Integer> teamIds);

    List<UserEntity> findByReportingTo(int managerId);
    List<UserEntity> findByReportingToIn(Set<Integer> managerIds);

    List<UserEntity> findByCreatedBy(int createdById);
    List<UserEntity> findByCreatedByIn(Set<Integer> createdByIds);

    List<UserEntity> findByEfficiency(double efficiency);
    List<UserEntity> findByEfficiencyLessThanEqual(double lessThanEfficiency);
    List<UserEntity> findByEfficiencyGreaterThanEqual(double greaterThanEfficiency);
    List<UserEntity> findByEfficiencyGreaterThanEqualAndEfficiencyLessThanEqual(double greaterThan, double lowerThan);
}
