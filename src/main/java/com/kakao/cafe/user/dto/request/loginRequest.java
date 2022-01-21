package com.kakao.cafe.user.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
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
}
