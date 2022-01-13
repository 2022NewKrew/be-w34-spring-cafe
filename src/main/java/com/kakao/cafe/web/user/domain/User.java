package com.kakao.cafe.web.user.domain;

public class User {
    private String userId;
    private String password;
    private String name;
    private String email;

    public String getUserId() { return this.userId; }
    public String getPassword() { return this.password; }
    public String getName() { return this.name; }
    public String getEmail() { return this.email; }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setPassword(String password) { this.password = password; }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}