package com.kakao.cafe.domain;

import com.kakao.cafe.domain.User;
import java.util.UUID;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserSignupRequest {

    @NotBlank
    @Size(min = 5, max = 10)
    private final String userId;
    @NotBlank
    @Size(min = 8, max = 20)
    private final String password;
    @NotBlank
    @Size(min = 5, max = 10)
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
