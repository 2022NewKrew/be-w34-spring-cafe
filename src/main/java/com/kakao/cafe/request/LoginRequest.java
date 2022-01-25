package com.kakao.cafe.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "아이디가 비어있습니다")
    private final String userId;
    @NotBlank(message = "비밀번호가 비어있습니다")
    private final String password;

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
