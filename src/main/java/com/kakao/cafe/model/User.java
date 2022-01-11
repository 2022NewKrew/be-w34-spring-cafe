package com.kakao.cafe.model;

public class User {
    private static int numOfUser = 0;
    private final int userId;
    private final String password;
    private final String name;
    private final String email;

    public User(String password, String name, String email) {
        this.userId = ++numOfUser;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
