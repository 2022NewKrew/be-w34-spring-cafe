package com.kakao.cafe.controller.dto;

import com.kakao.cafe.domain.user.dto.UserResponseDto;

public class SessionUser {
    private Long id;
    private String userId;
    private String name;
    private String email;

    public SessionUser() {}

    public SessionUser(UserResponseDto userResponseDto) {
        this.id = userResponseDto.getId();
        this.userId = userResponseDto.getUserId();
        this.name = userResponseDto.getName();
        this.email = userResponseDto.getEmail();
    }

    public String getUserId() {
        return userId;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
