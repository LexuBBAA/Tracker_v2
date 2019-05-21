package com.tracker.auth.ws.utils;

import org.springframework.lang.Nullable;

public interface UserEnrollValidator {
    boolean validateUsername(@Nullable String username);

    boolean validateEmail(@Nullable String email);

    boolean validatePassword(@Nullable String password);

    boolean validatePhoneNo(@Nullable String phone);
}
