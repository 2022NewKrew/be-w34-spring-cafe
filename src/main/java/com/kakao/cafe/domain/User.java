package com.kakao.cafe.domain;

import lombok.Getter;

@Getter
public class User {

    private int id;
    private String userId;
    private String password;
    private String name;
    private String email;

    public User(int id, String userId, String password, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
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
