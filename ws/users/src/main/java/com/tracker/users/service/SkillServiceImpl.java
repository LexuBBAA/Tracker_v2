package com.tracker.users.service;

import com.tracker.users.model.Skill;
import com.tracker.users.model.User;
import com.tracker.users.repository.SkillRepository;
import com.tracker.users.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.Predicate;
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
    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public Skill delete(long id) {
        return skillRepository.deleteById(id);
    }

    @Override
    public List<Skill> searchSkill(String name, String description, String type) {

        return getSkills().stream()
                .filter(getSkillPredicate(name, description, type).stream().reduce(x -> true, Predicate::and))
                .collect(Collectors.toList());
    }

    private List<Predicate<Skill>> getSkillPredicate(String name, String description, String type) {


        List<Predicate<Skill>> skillPredicates = new ArrayList<>();

        skillPredicates.add(Objects::nonNull);

        if (name != null) {
            skillPredicates.add(s -> s.getName().toLowerCase().contains(name.toLowerCase()));
        }

        if (description != null) {
            skillPredicates.add(s -> s.getDescription().toLowerCase().contains(description.toLowerCase()));
        }

        if (type != null) {
            skillPredicates.add(s -> s.getType().toLowerCase().contains(type.toLowerCase()));
        }

        return skillPredicates;
    }
}
