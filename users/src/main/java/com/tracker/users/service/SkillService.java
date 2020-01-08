package com.tracker.users.service;

import com.tracker.users.model.Skill;

import java.util.List;

public interface SkillService {

    List<Skill> getSkills();

    Skill getSkill(long id);

    List<Skill> findSkillByName(String name);

    Skill save(Skill skill);

    Skill delete(long id);
}
