package com.kakao.cafe.dto;

public class UserUpdateRequest {

    private final String userId;
    private final String password;
    private final String newPassword;
    private final String name;
    private final String email;

    public UserUpdateRequest(String userId, String password, String newPassword, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.newPassword = newPassword;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
