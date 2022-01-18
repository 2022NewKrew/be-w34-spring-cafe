package com.kakao.cafe.interfaces.user.dto.request;

import javax.validation.constraints.NotEmpty;

public class LoginRequestDto {
    @NotEmpty(message = "아이디가 비어있습니다.")
    private final String userId;

    @NotEmpty(message = "비밀번호가 비어있습니다.")
    private final String password;

    public LoginRequestDto(String userId, String password) {
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
