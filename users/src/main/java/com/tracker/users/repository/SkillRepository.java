package com.tracker.users.repository;

import com.tracker.users.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    List<Skill> findAll();

    Skill deleteById(long id);

    <S extends Skill> S save(S s);
}