package com.kakao.cafe.module.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {

    private final Long id;
    private final String userId;
    private final String password;
    private final String name;
    private final String email;
}
