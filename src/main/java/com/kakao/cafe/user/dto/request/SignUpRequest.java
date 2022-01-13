package com.kakao.cafe.user.dto.request;

import com.kakao.cafe.user.domain.User;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SignUpRequest {
    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String nickname;

    @NotNull
    private final String password;

    public SignUpRequest(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public User toUser(Long id) {
        return new User(id, this.email, this.nickname, this.password);
    }
}
