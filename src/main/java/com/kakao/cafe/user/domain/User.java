package com.kakao.cafe.user.domain;

import lombok.Getter;

import java.util.Objects;

public class User {
    @Getter private final String userId;
    private final String password;
    @Getter private final String name;
    @Getter private final String email;

    public User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public boolean isSameUser(String targetUserId) {
        return Objects.equals(this.userId, targetUserId);
    }
}
