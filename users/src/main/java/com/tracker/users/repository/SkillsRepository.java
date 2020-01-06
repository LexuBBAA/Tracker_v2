package com.tracker.users.repository;

import com.tracker.users.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillsRepository extends JpaRepository<Skill, Long> {

    List<Skill> findAll();

    <S extends Skill> S save(S s);

    <S extends Skill> S saveAndFlush(S s);
}
