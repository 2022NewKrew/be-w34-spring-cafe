package com.kakao.cafe.domain.entity;

import java.util.Map;

public class SignUp {

    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public SignUp(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public Map<String, ?> toMap() {
        return Map.of(
                "user_id", userId,
                "name", name,
                "password", password,
                "email", email
        );
    }

    public User createUser(long id) {
        return new User.Builder()
                .id(id)
                .userId(userId)
                .password(password)
                .name(name)
                .email(email)
                .build();
    }
}
