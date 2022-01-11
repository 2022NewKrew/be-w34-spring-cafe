package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.user.User;

public class UserProfileResponse {
    private final String name;
    private final String email;

    public UserProfileResponse(User user) {
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
