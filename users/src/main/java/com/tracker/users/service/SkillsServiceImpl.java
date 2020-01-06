package com.tracker.users.service;

import com.tracker.users.model.Skill;
import com.tracker.users.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SkillsServiceImpl implements SkillsService {

    @Autowired
    private SkillsRepository skillsRepository;

    private ConcurrentMap<Long, Skill> skillsById = new ConcurrentHashMap<>();

    @Scheduled(fixedDelay = 10_000)
    public void syncWithDb() {

        skillsById = skillsRepository.findAll()
                .stream()
                .collect(Collectors.toConcurrentMap(Skill::getId, Function.identity()));
    }

    @Override
    public List<Skill> getSkills() {

        return skillsById.values()
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<Skill> findSkillByName(String skillName) {
        return skillsById.values()
                .stream()
                .filter(Objects::nonNull)
                .filter(s -> Objects.equals(s.getName(), skillName))
                .collect(Collectors.toList());
    }

    @Override
    public Skill saveAndFlush(Skill user) {
        return skillsRepository.saveAndFlush(user);
    }

    @Override
    public Skill save(Skill skill) {
        return skillsRepository.save(skill);
    }
}
