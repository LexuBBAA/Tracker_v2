package com.tracker.users.service;

import com.tracker.users.model.User;

import java.util.List;

public interface UsersService {

    void syncWithDb();

    List<User> getUsers();

    List<User> findUserByName(String name);

    User saveAndFlush(User user);

    User save(User user);
}
