package com.kakao.cafe.dto;

public class LoginUserDto {
    private final String userId;
    private final String Password;

    public LoginUserDto(String userId, String password) {
        this.userId = userId;
        Password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return Password;
    }
}
