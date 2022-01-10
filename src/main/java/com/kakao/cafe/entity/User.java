package com.kakao.cafe.entity;

import com.kakao.cafe.dto.UserDto;

import java.util.Objects;

public class User implements Entity<UserDto> {

    private final String id;
    private final String name;
    private final String password;
    private final String email;

    private User(String id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public UserDto toDto() {
        return new UserDto.Builder()
                .email(email)
                .id(id)
                .name(name)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(password, user.password) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, email);
    }

    public static class Builder {

        private String id;
        private String name;
        private String password;
        private String email;

        public Builder id(String id) {
            this.id = id;
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
            return new User(id, name, password, email);
        }
    }
}
