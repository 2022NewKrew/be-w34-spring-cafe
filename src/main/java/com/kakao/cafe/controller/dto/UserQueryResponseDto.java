package com.kakao.cafe.controller.dto;

import com.kakao.cafe.domain.User;
import lombok.Getter;

@Getter
public class UserQueryResponseDto {

    private final String userId;
    private final String name;
    private final String email;

    public UserQueryResponseDto(User user) {
        userId = user.getUserId();
        name = user.getName();
        email = user.getEmail();
    }
}
