package com.kakao.cafe.user.dto;

import com.kakao.cafe.user.domain.User;

public class UserProfileResponse {

    public String userId;
    public String name;
    public String email;

    public UserProfileResponse(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public static UserProfileResponse valueOf(User user) {
        String userId = user.getUserId();
        String name = user.getName();
        String email = user.getEmail();
        return new UserProfileResponse(userId, name, email);
    }
}
