package com.kakao.cafe.user.dto;

import lombok.Getter;

@Getter
public class LoginDTO {
    private final String userId;
    private final String password;

    public LoginDTO(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
