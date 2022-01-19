package com.kakao.cafe.dto.user;

import lombok.Getter;

@Getter
public class UserDto {

    private final String id;
    private final String userName;
    private final String name;
    private final String email;

    public UserDto(String id, String userName, String name, String email) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.email = email;
    }
}
