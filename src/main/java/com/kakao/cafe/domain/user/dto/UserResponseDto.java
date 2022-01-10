package com.kakao.cafe.domain.user.dto;

import com.kakao.cafe.domain.user.User;

public class UserResponseDto {

    private Long id;
    private String userId;
    private String name;
    private String email;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
