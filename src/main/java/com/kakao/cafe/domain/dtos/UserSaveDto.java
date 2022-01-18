package com.kakao.cafe.domain.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserSaveDto {
    @NotBlank
    @Email
    private final String email;
    @NotBlank
    private final String name;
    @NotBlank
    private final String password;

    public UserSaveDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
