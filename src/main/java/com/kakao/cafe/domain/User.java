package com.kakao.cafe.domain;

public class User {
    private int userId;
    private String password;
    private String nickname;
    private String email;

    public User(){
    }

    public User(int ID, String password, String nickname, String email) {
        this.userId = ID;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
