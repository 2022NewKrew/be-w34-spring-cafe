package com.kakao.cafe.dto;

public class UserListResponseDto {

    String userId;
    String name;
    String email;

    public UserListResponseDto(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
}
