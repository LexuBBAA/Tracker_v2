package com.tracker.users.service;

import com.tracker.users.model.User;
import com.tracker.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    private ConcurrentMap<Long, User> usersById = new ConcurrentHashMap<>();

    @PostConstruct
    @Scheduled(fixedDelay = 10_000)
    private void syncWithDb() {

        ConcurrentMap<Long, User> tmpUsersById = usersRepository.findAll()
                .stream()
                .collect(Collectors.toConcurrentMap(User::getId, Function.identity()));

        usersById = tmpUsersById;
    }

    @Override
    public List<User> getUsers() {

        return usersById.values()
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
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
    public User saveAndFlush(User user) {
        return usersRepository.saveAndFlush(user);
    }

    @Override
    public User save(User user) {
        return usersRepository.save(user);
    }
}
