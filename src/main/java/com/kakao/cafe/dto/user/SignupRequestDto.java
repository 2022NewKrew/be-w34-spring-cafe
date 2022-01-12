package com.kakao.cafe.dto.user;

import javax.validation.constraints.NotBlank;

public class SignupRequestDto {

    @NotBlank
    private final String userName;
    @NotBlank
    private final String password;
    @NotBlank
    private final String name;
    @NotBlank
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
