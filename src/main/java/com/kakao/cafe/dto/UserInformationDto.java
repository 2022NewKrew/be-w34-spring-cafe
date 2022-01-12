package com.kakao.cafe.dto;

public class UserInformationDto {
    private final String userId;
    private final String name;
    private final String email;

    public UserInformationDto(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
}
