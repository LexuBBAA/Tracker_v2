package com.tracker.users.service;

import com.tracker.users.model.Skill;
import com.tracker.users.model.User;
import com.tracker.users.repository.UserRepository;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private ConcurrentMap<Long, User> usersById = new ConcurrentHashMap<>();

    @Scheduled(fixedDelay = Constants.SYNC_WITH_DB_TIMER)
    public void syncWithDb() {

        usersById = userRepository.findAll()
                .stream()
                .collect(Collectors.toConcurrentMap(User::getId, Function.identity()));
    }

    @Override
    public List<User> getUsers() {

        return usersById.values()
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public User getUser(long id) {
        return usersById.get(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User delete(long id) {
        return userRepository.deleteById(id);
    }

    @Override
    public List<User> searchUser(String firstName, String lastName, String userName, String email, String phone, Integer yearBorn, Float experience, Set<Long> skillIds) {

        return getUsers().stream()
                .filter(getUserPredicate(firstName, lastName, userName, email, phone, yearBorn, experience, skillIds).stream().reduce(x -> true, Predicate::and))
                .collect(Collectors.toList());
    }

    private List<Predicate<User>> getUserPredicate(String firstName, String lastName, String userName, String email, String phone, Integer yearBorn, Float experience, Set<Long> skillIds) {

        List<Predicate<User>> userPredicates = new ArrayList<>();

        userPredicates.add(Objects::nonNull);

        if (firstName != null) {
            userPredicates.add(u -> u.getFirstName().toLowerCase().contains(firstName.toLowerCase()));
        }

        if (lastName != null) {
            userPredicates.add(u -> u.getLastName().toLowerCase().contains(lastName.toLowerCase()));
        }

        if (userName != null) {
            userPredicates.add(u -> u.getUserName().toLowerCase().contains(userName.toLowerCase()));
        }

        if (email != null) {
            userPredicates.add(u -> u.getEmail().toLowerCase().contains(email.toLowerCase()));
        }

        if (phone != null) {
            userPredicates.add(u -> u.getPhone().toLowerCase().contains(phone.toLowerCase()));
        }

        if (yearBorn != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(yearBorn, Calendar.JANUARY, 1);

            userPredicates.add(u -> u.getBirthday().after(calendar.getTime()));
        }

        if (experience != null) {
            userPredicates.add(u -> u.getExperience() >= experience);
        }

        if (!CollectionUtils.isEmpty(skillIds)) {
            userPredicates.add(u -> u.getSkills().stream().map(Skill::getId).anyMatch(skillIds::contains));
        }

        return userPredicates;
    }
}
