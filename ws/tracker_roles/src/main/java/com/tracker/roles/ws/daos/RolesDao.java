package com.tracker.roles.ws.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tracker.roles.ws.entities.RoleEntity;

@Repository
public interface RolesDao extends CrudRepository<RoleEntity, Integer> {
	
}
