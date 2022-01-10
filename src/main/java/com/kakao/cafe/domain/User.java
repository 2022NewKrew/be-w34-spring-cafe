package com.kakao.cafe.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class User {

    private String userId;
    private int password;
    private String name;
    private String email;

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
