package com.kakao.cafe.domain;

public class User {

    public static int cnt = 1;

    private final int id;

    private String userId;
    private String pw;
    private String name;
    private String email;

    public User(int id, String userId, String pw, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.pw = pw;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
