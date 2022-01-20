package com.kakao.cafe.dto.user;

import com.kakao.cafe.domain.entity.User;

public class UserListShow {
    private final long userIndex;
    private final String userId;
    private final String name;
    private final String email;

    public UserListShow(long userIndex, String userId, String name, String email) {
        this.userIndex = userIndex;
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public UserListShow(long userIndex, User user) {
        this.userIndex = userIndex;
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public long getUserIndex() {
        return userIndex;
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
