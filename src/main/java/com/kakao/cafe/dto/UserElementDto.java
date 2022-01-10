package com.kakao.cafe.dto;

import com.kakao.cafe.model.User;

public class UserElementDto {
    private final String userId;
    private final String password;
    private final String name;
    private final String email;
    private final int index;

    public UserElementDto(User user, int index) {
        this.userId = user.getUserId();
        this.password = user.getPassword();
        this.name = user.getName();
        this.email = user.getEmail();
        this.index = index;
    }
}
