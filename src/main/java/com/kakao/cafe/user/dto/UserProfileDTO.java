package com.kakao.cafe.user.dto;

import com.kakao.cafe.user.domain.User;

public class UserProfileDTO {
    private String name;
    private String email;

    public UserProfileDTO(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
