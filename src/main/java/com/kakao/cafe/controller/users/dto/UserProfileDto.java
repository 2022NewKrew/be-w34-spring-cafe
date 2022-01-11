package com.kakao.cafe.controller.users.dto;

public class UserProfileDto {
    private final String userName;
    private final String email;

    public UserProfileDto(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "UserProfileDto{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
