package com.kakao.cafe.domain;

import java.util.UUID;

public class User {
    UUID id;
    String userId;
    String password;
    String name;
    String email;

    public User(String userId, String password, String name, String email) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return name;
    }
}
