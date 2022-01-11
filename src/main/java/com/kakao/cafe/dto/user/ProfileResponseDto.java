package com.kakao.cafe.dto.user;

public class ProfileResponseDto {

    private final String name;
    private final String email;

    public ProfileResponseDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
