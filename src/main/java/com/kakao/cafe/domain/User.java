package com.kakao.cafe.domain;

public class User {
    private String userId;
    private String userPassword;
    private String userName;
    private String email;

    public User() {}

    public User(String userId, String userPassword, String userName, String email) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.email = email;
    }

    public User(User user) {
        this.userId = user.getUserId();
        this.userPassword = user.getPassWord();
        this.userName = user.getUserName();
        this.email = user.getEmail();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassWord() {
        return userPassword;
    }

    public void setUserPassword(String password) {
        this.userPassword = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

