package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.user.User;

import java.util.Optional;

public class UserProfileResponse {
    private final String name;
    private final String email;

    public UserProfileResponse(Optional<User> user) {
        this.name = user.get().getName();
        this.email = user.get().getEmail();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
