package com.kakao.cafe.user.application.port.in;

import com.kakao.cafe.user.application.port.out.CreateUserDto;

public class SignUpUserDto {

    private final String email;
    private final String nickname;
    private final String password;

    public SignUpUserDto(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public CreateUserDto toCreateUserDto() {
        return new CreateUserDto(email, nickname, password);
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
