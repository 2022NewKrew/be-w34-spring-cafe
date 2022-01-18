package com.kakao.cafe.web;

import java.util.Objects;

public class User {

    private static final int INITIAL_POINT = 100;

    private Long id;
    private String email;
    private String nickname;
    private int point;

    public User() {
        this.point = INITIAL_POINT;
    }

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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void validate() {
        if (email == null || email.length() < 4 || nickname == null) {
            throw new IllegalArgumentException("Invalid User: " + this);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", point=" + point +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return point == user.point && id.equals(user.id) && email.equals(user.email) && nickname.equals(user.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, nickname, point);
    }
}
