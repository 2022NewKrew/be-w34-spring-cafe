package com.kakao.cafe.service.user.dto;

import com.kakao.cafe.model.user.User;

public class UserElementDto {

    private final String userId;
    private final String name;
    private final String email;
    private final int index;

    public UserElementDto(User user, int index) {
        this.userId = user.getUserId().getValue();
        this.name = user.getName().getValue();
        this.email = user.getEmail().getValue();
        this.index = index;
    }
}
