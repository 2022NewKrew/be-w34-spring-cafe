package com.kakao.cafe.user.adapter.in;

public class SignUpRequestDto {

    private final String email;
    private final String nickname;
    private final String password;

    public SignUpRequestDto(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }
}
