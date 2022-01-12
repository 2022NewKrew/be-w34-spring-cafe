package com.kakao.cafe.user.domain;

import lombok.Getter;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.kakao.cafe.common.exception.ExceptionMessage.INVALID_EMAIL_FORM_EXCEPTION;

@Getter
public class User {

    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    private User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public static User valueOf(String userId, String password, String name, String email) {
        validateEmail(email);
        return new User(userId, password, name, email);
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
