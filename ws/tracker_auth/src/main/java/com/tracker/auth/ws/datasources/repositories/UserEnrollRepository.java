package com.tracker.auth.ws.datasources.repositories;

import com.tracker.auth.ws.datasources.entities.UserEnrollEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEnrollRepository extends JpaRepository<UserEnrollEntity, Long> {
    UserEnrollEntity findByUsername(String username);
    UserEnrollEntity findByEmail(String email);
    UserEnrollEntity findByPhone(String phone);

    boolean existsByUserId(String userId);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
