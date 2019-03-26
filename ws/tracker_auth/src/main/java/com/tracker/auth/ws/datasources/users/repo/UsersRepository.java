package com.tracker.auth.ws.datasources.users.repo;

import com.tracker.auth.ws.datasources.users.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
}
