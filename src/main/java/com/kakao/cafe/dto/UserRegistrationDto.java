package com.kakao.cafe.dto;

import com.kakao.cafe.entity.User;

public class UserRegistrationDto {
    private String userId;
    private String password;
    private String email;
    private String profileImage;

    public User toEntity() {
        return new User(userId, password, email);
    }

    public UserRegistrationDto(String userId, String password, String email) {
        this.userId = userId;
        this.password = password;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
