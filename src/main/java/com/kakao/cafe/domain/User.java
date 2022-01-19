package com.kakao.cafe.domain;

public class User {

    private Integer id;
    private String userId;
    private String password;
    private String name;
    private String email;

    public User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public boolean isCorrectPassword(String comparedPassword) {
        return password.equals(comparedPassword);
    }

    public void updateUserProfile(User user) {
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.name = user.getName();
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
