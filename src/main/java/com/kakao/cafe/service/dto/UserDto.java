package com.kakao.cafe.service.dto;

import java.util.Objects;

public class UserDto {

    private final long id;
    private final String userId;
    private final String name;
    private final String email;

    private UserDto(long id, String userId, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return id == userDto.id && Objects.equals(userId, userDto.userId) && Objects.equals(name, userDto.name) && Objects.equals(email, userDto.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, email);
    }

    public static class Builder {

        private long id;
        private String userId;
        private String name;
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

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public UserDto build() {
            return new UserDto(id, userId, name, email);
        }
    }
}
