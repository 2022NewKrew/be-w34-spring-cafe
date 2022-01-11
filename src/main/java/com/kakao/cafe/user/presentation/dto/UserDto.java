package com.kakao.cafe.user.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {
    private final String userId;
    private final String name;
    private final String email;
}
