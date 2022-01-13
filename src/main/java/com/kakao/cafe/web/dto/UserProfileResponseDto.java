package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.user.User;

public class UserProfileResponseDto {

    private String userId;
    private String email;

    private UserProfileResponseDto(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public static UserProfileResponseDto from(User user) {
        return new UserProfileResponseDto(user.getUserId(), user.getEmail());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
