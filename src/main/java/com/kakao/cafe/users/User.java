package com.kakao.cafe.users;

public class User {
    private String ID;
    private String password;
    private String nickname;
    private String email;

    public User(String ID, String password, String nickname, String email) {
        this.ID = ID;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    public String getID() {
        return ID;
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
}
