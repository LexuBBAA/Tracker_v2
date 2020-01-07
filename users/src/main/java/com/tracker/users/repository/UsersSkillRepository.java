package com.tracker.users.repository;

import com.tracker.users.model.User;
import com.tracker.users.model.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersSkillRepository extends JpaRepository<UserSkill, Long> {

    <S extends User> S save(S s);
}
