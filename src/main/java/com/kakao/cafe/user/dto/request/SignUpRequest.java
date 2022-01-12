package com.kakao.cafe.user.dto.request;

import com.kakao.cafe.user.domain.User;

public class SignUpRequest {
    private final String email;
    private final String nickname;
    private final String password;

    public SignUpRequest(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public User toUser(Long id) {
        return new User(id, this.email, this.nickname, this.password);
    }
}
