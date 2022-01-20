package com.kakao.cafe.model;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class User {

    private final UUID id;
    private final String userId;
    private final String password;
    private final String name;
    private final String email;
    private final LocalDateTime createdAt;

    public User(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.password = builder.password;
        this.name = builder.name;
        this.email = builder.email;
        this.createdAt = builder.createdAt;
    }

    public static Optional<User> copyOptionalUser(Optional<User> original) {
        return original.map(user -> new Builder(
                        user.getId(),
                        user.getUserId(),
                        user.getPassword(),
                        user.getName(),
                        user.getEmail(),
                        user.getCreatedAt())
                        .build())
                .or(Optional::empty);
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean match(String password) {
        return this.password.equals(password);
    }

    public static class Builder {

        private final UUID id;
        private final String userId;
        private final String password;
        private final String name;
        private final String email;
        private final LocalDateTime createdAt;

        public Builder(String userId, String password, String name, String email) {
            this.id = UUID.randomUUID();
            this.userId = userId;
            this.password = password;
            this.name = name;
            this.email = email;
            this.createdAt = LocalDateTime.now();
        }

        public Builder(UUID id, String userId, String password, String name, String email, LocalDateTime createdAt) {
            this.id = id;
            this.userId = userId;
            this.password = password;
            this.name = name;
            this.email = email;
            this.createdAt = createdAt;
        }

        public User build() {
            return new User(this);
        }
    }
}
