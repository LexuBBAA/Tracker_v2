package com.tracker.users.ws.datasource.repositories;

import com.tracker.users.ws.datasource.entities.UserPreviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersPreviewRepository extends JpaRepository<UserPreviewEntity, Integer> {
    Optional<UserPreviewEntity> findByUsername(String username);
}
