package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.user.User;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfileResponseDto that = (UserProfileResponseDto) o;
        return Objects.equals(userId, that.userId) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email);
    }
}
