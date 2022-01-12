package com.kakao.cafe.dto.user;

public class UserCreateRequest {

    private final String username;
    private final String nickname;
    private final String email;
    private final String password;

    public UserCreateRequest(String username, String nickname, String email, String password) {
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
