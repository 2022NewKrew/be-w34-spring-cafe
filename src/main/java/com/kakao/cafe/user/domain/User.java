package com.kakao.cafe.user.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    private Long id;
    private String email;
    private String password;
    private String nickname;
    private LocalDateTime createdAt;

    public void signup(UserValidator userValidator) {
        userValidator.validateSignup(this);
    }

    public void login(UserValidator userValidator, String password) {
        userValidator.validateLogin(this.password, password);
    }
}
