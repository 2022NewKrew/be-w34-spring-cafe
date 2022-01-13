package com.kakao.cafe.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserSignupRequest {

    @NotBlank
    private final String userId;
    @NotBlank
    private final String password;
    @NotBlank
    private final String name;
    @NotBlank
    @Email
    private final String email;

    public UserSignupRequest(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User toEntity() {
        return new User(userId, password, name, email);
    }
}
