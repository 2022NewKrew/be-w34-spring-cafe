package com.kakao.cafe.model.Login;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginAuthDto {
    private final Long id;
    private final String email;
    private final String password;

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }
}
