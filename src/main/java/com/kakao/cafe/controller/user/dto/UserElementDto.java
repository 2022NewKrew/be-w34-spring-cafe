package com.kakao.cafe.controller.user.dto;

import com.kakao.cafe.model.user.User;

public class UserElementDto {

    private final String userId;
    private final String password;
    private final String name;
    private final String email;
    private final int index;

    public UserElementDto(User user, int index) {
        this.userId = user.getUserId().getValue();
        this.password = user.getPassword().getValue();
        this.name = user.getName().getValue();
        this.email = user.getEmail().getValue();
        this.index = index;
    }
}
