package com.kakao.cafe.dto.user;

import com.kakao.cafe.domain.entity.User;

public class UserProfileInfo {
    private final String name;
    private final String email;

    public UserProfileInfo(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserProfileInfo(User user) {
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
