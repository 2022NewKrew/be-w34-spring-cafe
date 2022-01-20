package com.kakao.cafe.login.application.port.in;

import lombok.Getter;

@Getter
public class UserLoginResult {
    private final Long id;
    private final String nickname;

    public UserLoginResult(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
