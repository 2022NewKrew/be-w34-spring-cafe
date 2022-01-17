package com.kakao.cafe.user.dto;

import com.kakao.cafe.user.domain.User;

public class UserUpdateDTO {
    final private String userId;
    private String name;
    private String email;

    public UserUpdateDTO(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
