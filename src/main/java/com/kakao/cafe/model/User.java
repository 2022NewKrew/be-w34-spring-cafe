package com.kakao.cafe.model;

import com.kakao.cafe.utility.NullChecker;

public class User {
    private final String userId;
    private final String password;
    private String name;
    private String email;

    public User(String userId, String password, String name, String email) {
        NullChecker.checkNotNull(userId);
        NullChecker.checkNotNull(password);
        NullChecker.checkNotNull(name);
        NullChecker.checkNotNull(email);

        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public boolean isUserId(String userId) {
        return this.userId.equals(userId);
    }

    public boolean isPassword(String password) {
        return this.password.equals(password);
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

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
