package com.kakao.cafe.controller.user;

import com.kakao.cafe.model.User;

public class UserInformationDto {
    private final String userId;
    private final String name;
    private final String email;

    public UserInformationDto(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
