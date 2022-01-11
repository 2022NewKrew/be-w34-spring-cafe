package com.kakao.cafe.controller.users.dto;

public class UserInfoDto {
    private final Long id;
    private final String userId;
    private final String userName;
    private final String email;

    public UserInfoDto(Long id, String userId, String userName, String email) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "UserInfoDto{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
