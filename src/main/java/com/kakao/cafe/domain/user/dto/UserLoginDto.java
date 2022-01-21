package com.kakao.cafe.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLoginDto {
    private final String email;
    private final String password;
}
