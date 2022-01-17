package com.kakao.cafe.user.dto;

public class UserLoginDTO {
    private String userId;
    private String password;

    public UserLoginDTO(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}
