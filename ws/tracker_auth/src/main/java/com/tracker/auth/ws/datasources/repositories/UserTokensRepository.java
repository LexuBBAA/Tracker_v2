package com.tracker.auth.ws.datasources.repositories;

import com.tracker.auth.ws.datasources.entities.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserTokensRepository extends JpaRepository<UserTokenEntity, Long> {
    UserTokenEntity findByUserId(String userId);
    UserTokenEntity findByTokenId(Long tokenId);

    boolean existsByUserId(String userId);
    boolean existsByTokenId(Long tokenId);

    @Transactional
    void deleteByUserId(String userId);
}
