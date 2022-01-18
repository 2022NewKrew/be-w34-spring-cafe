package com.kakao.cafe.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreateRequestDto {

    private final String userId;
    private final String password;
    private final String name;
    private final String email;
}
