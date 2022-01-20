package com.kakao.cafe.dto;

import javax.validation.constraints.NotBlank;

public class UserLoginDto {
    @NotBlank(message = "닉네임을 입력해주세요")
    private final String userId;
    @NotBlank(message = "패스워드를 입력해주세요")
    private final String password;

    public UserLoginDto(String userId, String password) {
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
