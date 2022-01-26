package com.kakao.cafe.dto;

import com.kakao.cafe.domain.User;

public class UserDto {
    private String userId;
    private String password;
    private String name;
    private String email;

    public UserDto() {
    }

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.password = user.getPassWord();
        this.name = user.getUserName();
        this.email = user.getEmail();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassWord() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

