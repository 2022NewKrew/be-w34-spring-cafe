package com.kakao.cafe.controller.users.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class SignUpRequestDto {
    private final String userId;
    private final String password;
    private final String name;
    private final String email;
}
