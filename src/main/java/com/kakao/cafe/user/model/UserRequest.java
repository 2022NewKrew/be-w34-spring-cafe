package com.kakao.cafe.user.model;

public class UserRequest {

    private final String userId;
    private final String name;
    private final String password;
    private final String email;

    public UserRequest(String userId, String name, String password, String email) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
