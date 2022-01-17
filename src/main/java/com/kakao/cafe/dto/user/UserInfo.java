package com.kakao.cafe.dto.user;

import com.kakao.cafe.domain.entity.User;

public class UserInfo {
    private final String userId;
    private final String name;
    private final String email;

    public UserInfo(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public UserInfo(User user) {
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
