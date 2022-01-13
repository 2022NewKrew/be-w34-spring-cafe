package com.kakao.cafe.model;

import java.util.UUID;

public class User {

    private final UUID id;
    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public User(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.password = builder.password;
        this.name = builder.name;
        this.email = builder.email;
    }

    public UUID getId() {
        return id;
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

    public static class Builder {
        private final UUID id;
        private final String userId;
        private final String password;
        private final String name;
        private final String email;

        public Builder(String userId, String password, String name, String email) {
            this.id = UUID.randomUUID();
            this.userId = userId;
            this.password = password;
            this.name = name;
            this.email = email;
        }

        public Builder(UUID id, String userId, String password, String name, String email) {
            this.id = id;
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
