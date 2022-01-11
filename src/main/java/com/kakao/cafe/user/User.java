package com.kakao.cafe.user;

public class User {
    private final String id;
    private final String name;
    private final String password;
    private final String email;

    public String getId() {
        return id;
    }

    public User(String id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
