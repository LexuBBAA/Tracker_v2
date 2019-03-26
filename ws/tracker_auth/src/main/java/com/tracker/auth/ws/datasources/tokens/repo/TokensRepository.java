package com.tracker.auth.ws.datasources.tokens.repo;

import com.tracker.auth.ws.datasources.tokens.entities.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface TokensRepository extends JpaRepository<TokenEntity, Integer> {
    TokenEntity findByDeviceId(@NonNull @Param("deviceId") String deviceId);

    boolean existsByToken(@NonNull @Param("token") String token);
}
