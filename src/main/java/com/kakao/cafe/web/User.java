package com.kakao.cafe.web;

public class User {

    private static final int INITIAL_POINT = 100;

    private Long id;
    private final String email;
    private String nickname;
    private int point;

    public User(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
        this.point = INITIAL_POINT;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public int getPoint() {
        return point;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void validate() {
        if (email == null || email.length() < 4 || nickname == null) {
            throw new IllegalArgumentException("Invalid User: " + this);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", point=" + point +
                '}';
    }
}
