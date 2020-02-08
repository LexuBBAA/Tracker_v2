package com.tracker.users.service;

import com.tracker.users.model.Skill;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface SkillService {

    List<Skill> getSkills();

    Skill getSkill(long id);

    Skill save(Skill skill);

    Skill delete(long id);

    List<Skill> searchSkill(String name, String description, String type);
}
