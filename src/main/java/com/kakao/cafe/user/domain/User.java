package com.kakao.cafe.user.domain;

import com.kakao.cafe.user.exception.CustomPasswordNotEqualsException;
import java.util.UUID;

public class User {

    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public UUID login(String inputPassword) {
        validatePassword(inputPassword);
        return UUID.randomUUID();
    }

    public void validatePassword(String inputPassword) {
        if (!password.equals(inputPassword)) {
            throw new CustomPasswordNotEqualsException();
        }
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
