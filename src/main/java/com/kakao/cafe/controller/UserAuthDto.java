package com.kakao.cafe.controller;


public class UserAuthDto {

    private String userId;
    private String password;

    public UserAuthDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}
