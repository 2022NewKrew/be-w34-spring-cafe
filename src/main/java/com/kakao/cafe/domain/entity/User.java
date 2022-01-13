package com.kakao.cafe.domain.entity;

public class User {
    private final long userIndex;
    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public User(long userIndex, String userId, String password, String name, String email) {
        this.userIndex = userIndex;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public long getUserIndex() { return userIndex; }

    public String getUserId() { return userId; }

    public String getPassword() { return password; }

    public String getName() { return name; }

    public String getEmail() { return email; }
}
