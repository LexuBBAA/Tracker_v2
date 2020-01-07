package com.tracker.users.service;

import com.tracker.users.model.Skill;

import java.util.List;

public interface SkillsService {

    void syncWithDb();

    List<Skill> getSkills();

    Skill getSkill(long id);

    List<Skill> findSkillByName(String name);

    Skill saveAndFlush(Skill skill);

    Skill save(Skill skill);
}
