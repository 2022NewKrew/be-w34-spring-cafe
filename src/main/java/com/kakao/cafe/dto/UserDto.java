package com.kakao.cafe.dto;

import com.kakao.cafe.entity.User;

public class UserDto implements Dto<User> {

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

    public String getUserId() {
        return userId;
    }

    @Override
    public User toEntity() {
        return new User.Builder()
                .email(email)
                .id(userId)
                .name(name)
                .build();
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
