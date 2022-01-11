package com.kakao.cafe.service.dto;

public class CredentialsDto {

    private final String userId;
    private final String password;

    public CredentialsDto(String userId, String password) {
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
