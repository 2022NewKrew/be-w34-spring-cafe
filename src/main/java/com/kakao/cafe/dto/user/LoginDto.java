package com.kakao.cafe.dto.user;

import javax.validation.constraints.NotBlank;

public class LoginDto {

    @NotBlank(message = "필수 입력 항목이 누락되었습니다: 아이디")
    private final String userName;
    @NotBlank(message = "필수 입력 항목이 누락되었습니다: 비밀번호")
    private final String password;

    public LoginDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
