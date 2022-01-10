package com.kakao.cafe.model;

public class User {
    private static long offset = 0;
    private final long id;
    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public User(String userId, String password, String name, String email) {
        offset += 1;
        this.id = offset;
        this.userId = userId;
        this.password = password;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
