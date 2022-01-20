package com.kakao.cafe.user.domain;

import com.kakao.cafe.common.exception.AuthenticationException;
import lombok.Getter;
import lombok.NonNull;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.kakao.cafe.common.exception.ExceptionMessage.*;

@Getter
public class User {

    @NonNull
    private final String userId;

    @NonNull
    private String password;

    @NonNull
    private String name;

    @NonNull
    private String email;

    private User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public static User valueOf(String userId, String password, String name, String email) {
        validatePropertiesLengths(userId, password, name);
        validateEmail(email);
        return new User(userId, password, name, email);
    }

    public void update(String password, String name, String email) {
        validatePropertiesLengths(userId, password, name);
        validateEmail(email);
        this.password = password;
        this.name = name;
        this.email = email;
    }

    private static void validatePropertiesLengths(String userId, String password, String name) {
        validateUserId(userId);
        validatePassword(password);
        validateName(name);
    }

    private static void validateUserId(String userId) throws IllegalArgumentException {
        if (userId.trim().length() == 0) {
            throw new IllegalArgumentException(VALUE_LENGTH_LOWERBOUND_EXCEPTION + "\nreason: userId");
        }
        if (userId.length() > USER_ID_LENGTH_UPPERBOUND) {
            throw new IllegalArgumentException(USER_ID_LENGTH_UPPERBOUND_EXCEPTION);
        }
    }

    private static void validatePassword(String password) throws IllegalArgumentException {
        if (password.trim().length() == 0) {
            throw new IllegalArgumentException(VALUE_LENGTH_LOWERBOUND_EXCEPTION + "\nreason: password");
        }
        if (password.length() > PASSWORD_LENGTH_UPPERBOUND) {
            throw new IllegalArgumentException(PASSWORD_LENGTH_UPPERBOUND_EXCEPTION);
        }
    }

    private static void validateName(String name) throws IllegalArgumentException {
        if (name.trim().length() == 0) {
            throw new IllegalArgumentException(VALUE_LENGTH_LOWERBOUND_EXCEPTION + "\nreason: name");
        }
        if (name.trim().length() > NAME_LENGTH_UPPERBOUND) {
            throw new IllegalArgumentException(NAME_LENGTH_UPPERBOUND_EXCEPTION);
        }
    }

    private static void validateEmail(String email) throws IllegalArgumentException {
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(INVALID_EMAIL_FORM_EXCEPTION);
        }
    }

    public void verifyPassword(String password) throws IllegalArgumentException {
        if (!this.getPassword().equals(password)) {
            AuthenticationException.throwAuthFailure(NOT_MATCHING_PASSWORD_EXCEPTION, password);
        }
    }

    public boolean isSameUserById(String targetUserId) {
        return Objects.equals(this.userId, targetUserId);
    }

    private static final int USER_ID_LENGTH_UPPERBOUND = 20;
    private static final int PASSWORD_LENGTH_UPPERBOUND = 20;
    private static final int NAME_LENGTH_UPPERBOUND = 10;
}
