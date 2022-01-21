package com.kakao.cafe.domain.entity;

import com.kakao.cafe.service.dto.UserDto;

import java.util.Objects;

public class User {

    private long id;
    private final String userId;
    private final String name;
    private final String password;
    private final String email;

    private User(long id, String userId, String name, String password, String email) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public boolean canModify(User target, String password) {
        return this.id == target.id && Objects.equals(password, target.password);
    }

    public boolean canDelete(Reply target) {
        return this.id == target.getAuthorId();
    }

    public UserDto toDto() {
        return new UserDto.Builder()
                .id(id)
                .email(email)
                .userId(userId)
                .name(name)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(name, user.name) && Objects.equals(password, user.password) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, password, email);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public static class Builder {

        private long id;
        private String userId;
        private String name;
        private String password;
        private String email;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(id, userId, name, password, email);
        }
    }
}
