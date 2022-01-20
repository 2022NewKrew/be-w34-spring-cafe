package com.kakao.cafe.model;

public class User {
    private String userId;
    private String password;
    private String email;
    private String name;
    private String createdAt;

    public User() {
    }
    public User(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }
    public User(String userId, String email, String name, String createdAt) {
        this.userId = userId;
        this.email = email;
        this.name=name;
        this.createdAt = createdAt;
    }

    // getter
    public String getUserId() {
        return userId;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }

    // setter
    public void setUserId(String userId) {
            this.userId = userId;
        }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }
}
