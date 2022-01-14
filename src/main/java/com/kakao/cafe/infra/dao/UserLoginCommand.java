package com.kakao.cafe.infra.dao;

public class UserLoginCommand {
    private final Long id;
    private final String nickname;
    private final String password;

    public UserLoginCommand(Long id, String nickname, String password) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
    }
}
