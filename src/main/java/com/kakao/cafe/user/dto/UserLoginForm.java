package com.kakao.cafe.user.dto;

import javax.validation.constraints.NotBlank;

public class UserLoginForm {
    @NotBlank
    private final String username;

    @NotBlank
    private final String password;

    public UserLoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
