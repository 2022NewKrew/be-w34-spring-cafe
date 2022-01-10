package com.kakao.cafe.domain;

import java.util.Objects;

public class User {
    private final Long id;
    private final String userId;
    private String password;
    private String name;
    private String email;

    public static class Builder {
        private final Long id;
        private final String userId;
        private String password;
        private String name = "";
        private String email = "";

        public Builder(Long id, String userId, String password) {
            this.id = id;
            this.userId = userId;
            this.password = password;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    private User(Builder builder) {
        id = builder.id;
        userId = builder.userId;
        password = builder.password;
        name = builder.name;
        email = builder.email;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
