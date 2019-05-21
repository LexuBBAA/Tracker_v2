package com.tracker.auth.ws.datasources.repositories;

import com.tracker.auth.ws.datasources.entities.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TokensRepository extends JpaRepository<TokenEntity, Long> {
    TokenEntity findByToken(String token);
    TokenEntity findByRefreshToken(String refreshToken);

    boolean existsByToken(String token);
    boolean existsByRefreshToken(String refreshToken);

    @Transactional
    void deleteByToken(String token);
}
