package com.kakao.cafe.service.user.dto;

public class UserUpdateForm {
    private final Long id;
    private final String userId;
    private final String password;
    private final String userName;
    private final String email;

    public UserUpdateForm(Long id, String userId, String password, String userName, String email) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }
}
