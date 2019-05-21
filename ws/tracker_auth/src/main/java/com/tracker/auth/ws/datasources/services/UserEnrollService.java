package com.tracker.auth.ws.datasources.services;

import com.tracker.auth.ws.datasources.dtos.users.UserEnrollDto;

public interface UserEnrollService {
    UserEnrollDto findByUsername(String input);
    UserEnrollDto findByEmail(String input);
    UserEnrollDto findByPhone(String input);

    UserEnrollDto createUser(UserEnrollDto newUser);
    UserEnrollDto updatePassword(UserEnrollDto userEnrollDto);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
