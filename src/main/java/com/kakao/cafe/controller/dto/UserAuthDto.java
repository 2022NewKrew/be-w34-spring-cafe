package com.kakao.cafe.controller.dto;


public class UserAuthDto {

    private String userId;
    private String password;

    private UserAuthDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format(
                "UserAuthDto{userId=%s, password=%s}",
                userId,
                password);
    }
}
