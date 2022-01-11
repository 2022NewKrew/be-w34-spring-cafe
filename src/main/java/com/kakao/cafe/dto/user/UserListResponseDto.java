package com.kakao.cafe.dto.user;

public class UserListResponseDto {

    private final String userId;
    private final String name;
    private final String email;

    public UserListResponseDto(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
}
