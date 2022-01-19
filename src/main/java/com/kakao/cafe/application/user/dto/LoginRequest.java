package com.kakao.cafe.application.user.dto;

public class LoginRequest {

    final private String userId;
    final private String password;

    public LoginRequest(String userId, String password) {
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
