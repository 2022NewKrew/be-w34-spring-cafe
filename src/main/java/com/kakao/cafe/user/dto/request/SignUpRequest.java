package com.kakao.cafe.user.dto.request;

import com.kakao.cafe.user.domain.User;
import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SignUpRequest {
    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String nickname;

    @NotBlank
    private final String password;

    private SignUpRequest(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public User toUser() {
        return User.builder()
            .email(this.email)
            .nickname(this.nickname)
            .password(this.password)
            .createdDate(LocalDateTime.now())
            .build();
    }
}
