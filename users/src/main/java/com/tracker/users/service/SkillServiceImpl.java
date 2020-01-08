package com.tracker.users.service;

import com.tracker.users.model.Skill;
import com.tracker.users.repository.SkillRepository;
import com.tracker.users.utils.Constants;
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
public class SkillServiceImpl implements SkillService {


    @Autowired
    private SkillRepository skillRepository;

    private ConcurrentMap<Long, Skill> skillsById = new ConcurrentHashMap<>();

    @Scheduled(fixedDelay = Constants.SYNC_WITH_DB_TIMER)
    public void syncWithDb() {

        skillsById = skillRepository.findAll()
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
    public Skill getSkill(long id) {
        return skillsById.get(id);
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
    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public Skill delete(long id) {
        return skillRepository.deleteById(id);
    }
}
