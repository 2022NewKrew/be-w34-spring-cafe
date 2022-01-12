package com.kakao.cafe.dto;

public class UserDto {

    private final String email;
    private final String password;
    private final String nickname;

    public UserDto(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }
}
