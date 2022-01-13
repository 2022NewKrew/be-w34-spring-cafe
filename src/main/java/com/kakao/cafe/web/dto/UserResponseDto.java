package com.kakao.cafe.web.dto;

import lombok.Getter;

@Getter
public class UserResponseDto {

    private Long id;
    private String name;
    private String email;

    public UserResponseDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

}
