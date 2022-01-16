package com.kakao.cafe.web.dto.user;

import lombok.Getter;

@Getter
public class UserCreateRequestDto {
    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public UserCreateRequestDto(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }


}
