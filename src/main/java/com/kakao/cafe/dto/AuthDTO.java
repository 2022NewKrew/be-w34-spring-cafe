package com.kakao.cafe.dto;

import javax.validation.constraints.NotBlank;

public class AuthDTO {
    @NotBlank
    private final String userId;
    @NotBlank
    private final String password;

    public AuthDTO(String userId, String password) {
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
