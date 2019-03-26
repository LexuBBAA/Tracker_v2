package com.tracker.auth.ws.datasources.users.repo;

import com.tracker.auth.ws.datasources.users.entities.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DevicesRepository extends JpaRepository<DeviceEntity, String> {
    Collection<DeviceEntity> findByUserId(@NonNull long userId);
}
