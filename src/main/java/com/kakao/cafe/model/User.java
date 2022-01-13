package com.kakao.cafe.model;

public class User {
    private String userId;
    private String password;
    private String email;

    public User() {}
    public User(String userId, String email) {
        this.userId = userId;
        this.email = email;
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
}
