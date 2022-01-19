package com.kakao.cafe.service.user.dto;

import com.kakao.cafe.model.user.User;

public class UserInformationDto {

    private final String userId;
    private final String name;
    private final String email;

    public UserInformationDto(User user) {
        this.userId = user.getUserId().getValue();
        this.name = user.getName().getValue();
        this.email = user.getEmail().getValue();
    }
}
