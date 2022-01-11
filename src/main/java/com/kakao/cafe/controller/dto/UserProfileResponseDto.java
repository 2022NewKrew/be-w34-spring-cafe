package com.kakao.cafe.controller.dto;

import com.kakao.cafe.domain.User;
import lombok.Getter;

@Getter
public class UserProfileResponseDto {

    private final String userId;
    private final String email;

    public UserProfileResponseDto(User foundUser) {
        userId = foundUser.getUserId();
        email = foundUser.getEmail();
    }
}
