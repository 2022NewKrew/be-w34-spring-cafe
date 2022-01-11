package com.kakao.cafe.app.request;

import com.kakao.cafe.service.dto.CredentialsDto;

public class LoginRequest {

    private final String userId;
    private final String password;

    public LoginRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public CredentialsDto toCredentialsDto() {
        return new CredentialsDto(userId, password);
    }
}
