package com.kakao.cafe.domain;

public class User {
    private Long id;
    private final String userId;
    private final String password;
    private final String userName;
    private final String email;

    public User(String userId, String password, String userName, String email) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
    }

    public User(Long id, String userId, String password, String userName, String email) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
    }

    public static User of(String userId, String password, String userName, String email) {
        return new User(userId, password, userName, email);
    }

    public static User createEmptyUser() {
        return new User(0L, "", "", "", "");
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("User{id=%d, userId=%s, password=%s, userName=%s, email=%s}", id, userId, password, userName, email);
    }
}
