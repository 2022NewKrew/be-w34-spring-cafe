package com.kakao.cafe.controller.dto;

import com.kakao.cafe.domain.User;
import lombok.Getter;

@Getter
public class UserProfileResponseDto {

    private final String name;
    private final String email;

    public UserProfileResponseDto(User foundUser) {
        name = foundUser.getName();
        email = foundUser.getEmail();
    }
}
