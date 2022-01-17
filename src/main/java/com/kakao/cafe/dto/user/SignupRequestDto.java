package com.kakao.cafe.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SignupRequestDto {

    @NotBlank(message = "필수 입력 항목이 누락되었습니다: 아이디")
    private final String userName;
    @NotBlank(message = "필수 입력 항목이 누락되었습니다: 비밀번호")
    private final String password;
    @NotBlank(message = "필수 입력 항목이 누락되었습니다: 이름")
    private final String name;
    @NotBlank(message = "필수 입력 항목이 누락되었습니다: 이메일")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private final String email;

    public SignupRequestDto(String userName, String password, String name, String email) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
