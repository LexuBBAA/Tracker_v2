package com.tracker.users.service;

import com.tracker.users.model.User;
import com.tracker.users.repository.UserRepository;
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
    public List<User> findUserByName(String useraName) {
        return usersById.values()
                .stream()
                .filter(Objects::nonNull)
                .filter(u -> Objects.equals(u.getUserName(), useraName))
                .collect(Collectors.toList());
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User delete(long id) {
        return userRepository.deleteById(id);
    }
}
