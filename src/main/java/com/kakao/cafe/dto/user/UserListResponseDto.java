package com.kakao.cafe.dto.user;

public class UserListResponseDto {

    private final String id;
    private final String userName;
    private final String name;
    private final String email;

    public UserListResponseDto(String id, String userName, String name, String email) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.email = email;
    }
}
