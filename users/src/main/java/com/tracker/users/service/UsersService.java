package com.tracker.users.service;

import com.tracker.users.model.User;

import java.util.List;

public interface UsersService {

    List<User> getUsers();

    User getUser(long id);

    List<User> findUserByName(String name);

    User save(User user);

    User delete(long id);
}
