package com.kakao.cafe.login.application.port.in;

import lombok.Getter;

@Getter
public class UserLoginCommand {
    private final Long id;
    private final String nickname;

    public UserLoginCommand(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
