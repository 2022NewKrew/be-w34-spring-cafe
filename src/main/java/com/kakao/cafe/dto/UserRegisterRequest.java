package com.kakao.cafe.dto;

import com.kakao.cafe.model.User;

public class UserRegisterRequest {

    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public UserRegisterRequest(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User toEntity() {
        return new User.Builder(userId, password, name, email).build();
    }
}
