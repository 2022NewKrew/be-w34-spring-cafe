package com.kakao.cafe.Dto.Login;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginAuthDto {
    private final Long id;
    private final String email;
    private final String password;
}
