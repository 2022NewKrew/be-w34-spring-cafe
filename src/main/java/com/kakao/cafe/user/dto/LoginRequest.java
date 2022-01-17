package com.kakao.cafe.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginRequest {

    @NotBlank
    @Email(regexp = "\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b")
    private final String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private final String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
