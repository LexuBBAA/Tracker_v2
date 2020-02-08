package com.tracker.users.service;

import com.tracker.users.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> getUsers();

    User getUser(long id);

    User save(User user);

    User delete(long id);

    List<User> searchUser(String firstName, String lastName, String userName, String email, String phone, Integer yearBorn, Float experience, Set<Long> skillIds);
}
