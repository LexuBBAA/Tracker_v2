package com.tracker.users.ws.datasource.repositories;

import com.tracker.users.ws.datasource.entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Integer> {
	
	boolean existsByUserId(String userId);
	UserEntity findByUserId(String userId);
	
	@Transactional
	void deleteByUserId(String userId);
	
	List<UserEntity> findAllByUsernameLike(String username);
	List<UserEntity> findAllByCreatedBy(String createdBy);
}
