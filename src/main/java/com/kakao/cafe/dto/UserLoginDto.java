package com.kakao.cafe.dto;

import javax.validation.constraints.NotBlank;

public class UserLoginDto {
    @NotBlank(message = "유저 닉네임이 null 이거나 한개의 띄어쓰기만 있습니다")
    private final String userId;
    @NotBlank(message = "유저 패스워드가 null 이거나 한개의 띄어쓰기만 있습니다")
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
