package com.kakao.cafe.domain.dto.user;

import com.kakao.cafe.domain.entity.User;

public class UserInfo {
    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public UserInfo(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public UserInfo(User user) {
        this.userId = user.getUserId();
        this.password = user.getPassword();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
