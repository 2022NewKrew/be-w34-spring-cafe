package com.kakao.cafe.model;

import java.util.concurrent.atomic.AtomicInteger;

public class User {

    private static final AtomicInteger idGenerator;

    private final int id;

    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    static {
        idGenerator = new AtomicInteger(1);
    }

    public static User of(String userId, String password, String name, String email) {
        return new User(userId, password, name, email);
    }

    private User(String userId, String password, String name, String email) {
        this.id = idGenerator.getAndIncrement();
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", userId='" + userId + '\'' +
            ", password='" + password + '\'' +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
