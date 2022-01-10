package com.kakao.cafe.user;

public class User {
    public String userId;
    public String password;
    public String name;
    public String email;

    public User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public boolean isSameUser(String targetUserId) {
        return this.userId == targetUserId;
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
}
