package com.kakao.cafe.user.dto;

import com.kakao.cafe.user.domain.User;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserSignupRequest {

    @NotBlank
    @Size(min = 2, max = 15)
    private final String username;

    @NotBlank
    @Size(min = 8, max = 20)
    private final String password;

    @NotBlank
    @Size(min = 2, max = 15)
    private final String name;

    @Email
    private final String email;

    public UserSignupRequest(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User toEntity() {
        return User.builder()
            .username(username)
            .password(password)
            .name(name)
            .email(email)
            .build();
    }
}
