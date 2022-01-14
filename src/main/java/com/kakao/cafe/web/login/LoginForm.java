package com.kakao.cafe.web.login;

public class LoginForm {
    private final String nickname;
    private final String password;

    public LoginForm(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }
}
