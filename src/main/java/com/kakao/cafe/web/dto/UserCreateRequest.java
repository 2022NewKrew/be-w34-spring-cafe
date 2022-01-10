package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.user.User;

public class UserCreateRequest {
    private String userId;
    private String password;
    private String name;
    private String email;

    public UserCreateRequest() {

    }

    public UserCreateRequest(User user) {
        this.userId = user.getUserId();
        this.password = user.getPassword();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public User toEntity() {
        User user = new User();

        user.setUserId(userId);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);

        return user;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
