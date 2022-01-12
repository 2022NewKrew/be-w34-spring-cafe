package com.kakao.cafe.model;

import java.util.UUID;

public class User {

    private final UUID id;
    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public User(Builder builder) {
        this.id = UUID.randomUUID();
        this.userId = builder.userId;
        this.password = builder.password;
        this.name = builder.name;
        this.email = builder.email;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private final String userId;
        private final String password;
        private final String name;
        private final String email;

        public Builder(String userId, String password, String name, String email) {
            this.userId = userId;
            this.password = password;
            this.name = name;
            this.email = email;
        }

        public User build() {
            return new User(this);
        }
    }
}
