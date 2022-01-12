package com.kakao.cafe.dto.user;

import com.kakao.cafe.domain.user.User;

public class UserCreateRequest {

    private final String username;
    private final String nickname;
    private final String email;
    private final String password;

    public UserCreateRequest(String username, String nickname, String email, String password) {
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public User toEntity() {
        return new User(username, nickname, email, password);
    }
}
