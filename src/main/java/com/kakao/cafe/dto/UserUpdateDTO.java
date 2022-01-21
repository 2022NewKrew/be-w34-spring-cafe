package com.kakao.cafe.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserUpdateDTO {
    @NotBlank
    private final String userId;
    @NotBlank
    private final String password;
    @NotBlank
    private final String passwordCheck;
    @NotBlank
    private final String name;
    @NotBlank
    @Pattern(regexp = "^(.+)@(.+)$")
    private final String email;

    private UserUpdateDTO(String userId, String password, String passwordCheck, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
