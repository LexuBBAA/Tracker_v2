package com.tracker.auth.ws.utils;

import org.springframework.lang.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class UserEnrollValidatorImpl implements UserEnrollValidator {
    private static final Pattern USERNAME_PATTERN =
            Pattern.compile("^[a-zA-Z0-9]+([-_.@]?)*[a-zA-Z0-9]+$");
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile(
                    "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
    private static final Pattern PHONE_NO_PATTERN =
            Pattern.compile("\\d{10}");

    @Override
    public boolean validateUsername(@Nullable String username) {
        if(isNullOrEmpty(username)) {
            return false;
        }
        Matcher matcher = USERNAME_PATTERN.matcher(username);
        return matcher.matches();
    }

    @Override
    public boolean validateEmail(@Nullable String email) {
        if(isNullOrEmpty(email)) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    @Override
    public boolean validatePassword(@Nullable String password) {
        if(isNullOrEmpty(password)) {
            return false;
        }
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        return matcher.matches() && password.length() > 5;
    }

    @Override
    public boolean validatePhoneNo(@Nullable String phone) {
        if(isNullOrEmpty(phone)) {
           return false;
        }
        Matcher matcher = PHONE_NO_PATTERN.matcher(phone);
        return matcher.matches();
    }

    private boolean isNullOrEmpty(@Nullable String input) {
        return input == null || input.isEmpty();
    }
}
