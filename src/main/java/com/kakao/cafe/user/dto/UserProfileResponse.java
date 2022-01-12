package com.kakao.cafe.user.dto;

import java.util.Objects;

public class UserProfileResponse {

    public String userId;
    public String name;
    public String email;

    public UserProfileResponse(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public static UserProfileResponse valueOf(UserListResponse userListResponse) {
        String userId = userListResponse.userId;
        String name = userListResponse.name;
        String email = userListResponse.email;
        return new UserProfileResponse(userId, name, email);
    }

    public boolean isSameUser(String targetUserId) {
        return Objects.equals(this.userId, targetUserId);
    }
}
