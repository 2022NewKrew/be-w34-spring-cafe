package com.kakao.cafe.domain;

import lombok.AllArgsConstructor;


public class User {

    private static int lastId = 1;
    private int id;
    private String userId;
    private int password;
    private String name;
    private String email;

    public User(String userId, int password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.id = lastId++;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public int getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userId='" + userId + '\'' +
                ", password=" + password +
                '}';
    }
}
