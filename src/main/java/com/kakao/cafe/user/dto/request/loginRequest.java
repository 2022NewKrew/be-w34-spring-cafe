package com.kakao.cafe.user.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class loginRequest {
    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String password;

    public loginRequest(String email, String password) {
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
