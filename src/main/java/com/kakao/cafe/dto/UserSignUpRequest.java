package com.kakao.cafe.dto;

public class UserSignUpRequest {

    private final String userId;
    private final String pw;
    private final String name;
    private final String email;

    public UserSignUpRequest(String userId, String pw, String name, String email) {
        this.userId = userId;
        this.pw = pw;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPw() {
        return pw;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
