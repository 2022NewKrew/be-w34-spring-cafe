package com.kakao.cafe.user.domain;

import lombok.Getter;
import lombok.NonNull;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.kakao.cafe.common.exception.ExceptionMessage.INVALID_EMAIL_FORM_EXCEPTION;
import static com.kakao.cafe.common.exception.ExceptionMessage.NON_NULL_EXCEPTION;

@Getter
public class User {

    @NonNull
    private final String userId;

    @NonNull
    private final String password;

    @NonNull
    private final String name;

    @NonNull
    private final String email;

    private User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public static User valueOf(String userId, String password, String name, String email) {
        validateLength(userId, password, name);
        validateEmail(email);
        return new User(userId, password, name, email);
    }

    private static void validateLength(String userId, String password, String name) {
        if (userId.trim().length() == 0 || password.trim().length() == 0 || name.trim().length() == 0) {
            throw new IllegalArgumentException(NON_NULL_EXCEPTION);
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

    public boolean isSameUserById(String targetUserId) {
        return Objects.equals(this.userId, targetUserId);
    }

    public boolean isSameUserByName(String targetUserName) {
        return Objects.equals(this.name, targetUserName);
    }
}
