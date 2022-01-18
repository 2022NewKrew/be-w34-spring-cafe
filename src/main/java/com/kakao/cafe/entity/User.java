package com.kakao.cafe.entity;

public class User {
    private final Integer id;
    private final String userId;
    private final String password;
    private final String email;

    public User(Integer id, String userId, String password, String email) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() { return email;}

    public String getPassword() {
        return password;
    }
}
