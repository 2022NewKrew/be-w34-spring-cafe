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
                        user.getUserId(),
                        user.getPassword(),
                        user.getName(),
                        user.getEmail())
                        .id(user.getId())
                        .createdAt(user.getCreatedAt())
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

        private UUID id = UUID.randomUUID();
        private final String userId;
        private final String password;
        private final String name;
        private final String email;
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder(String userId, String password, String name, String email) {
            this.userId = userId;
            this.password = password;
            this.name = name;
            this.email = email;
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
