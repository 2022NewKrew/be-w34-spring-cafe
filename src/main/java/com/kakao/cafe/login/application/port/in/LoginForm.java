package com.kakao.cafe.login.application.port.in;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginForm {
    @NotEmpty(message = "아이디는 필수사항입니다.")
    @Size(min = 3, max = 15, message = "아이디는 3글자 이상 15글자 이하입니다.")
    private final String nickname;

    @NotEmpty(message = "패스워드는 필수사항입니다.")
    @Size(min = 8, message = "패스워드는 8글자 이상이어야 합니다.")
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
