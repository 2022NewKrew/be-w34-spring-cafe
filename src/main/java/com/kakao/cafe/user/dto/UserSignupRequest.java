package com.kakao.cafe.user.dto;

import com.kakao.cafe.user.domain.User;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserSignupRequest {

    @NotBlank
    @Email(regexp = "\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b")
    private final String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private final String password;

    @NotBlank
    @Size(min = 2, max = 10)
    private final String username;

    public UserSignupRequest(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public User toEntity() {
        return User.builder()
            .email(email)
            .password(password)
            .username(username)
            .build();
    }
}
