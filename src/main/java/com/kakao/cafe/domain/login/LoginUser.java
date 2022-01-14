package com.kakao.cafe.domain.login;

public class LoginUser {
    private final Long id;
    private final String nickname;
    private final String password;

    public LoginUser(Long id, String nickname, String password) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
    }
}
