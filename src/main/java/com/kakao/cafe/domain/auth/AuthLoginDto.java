package com.kakao.cafe.domain.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AuthLoginDto {
    @NotBlank
    @Email
    private final String email;
    @NotBlank
    private final String password;

    public AuthLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
