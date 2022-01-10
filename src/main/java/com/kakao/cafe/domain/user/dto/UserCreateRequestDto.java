package com.kakao.cafe.domain.user.dto;

import com.kakao.cafe.domain.user.User;

public class UserCreateRequestDto {

    private String userId;
    private String password;
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserId() {
        return userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User toUser() {
        return new User(userId, password, name, email);
    }
}
