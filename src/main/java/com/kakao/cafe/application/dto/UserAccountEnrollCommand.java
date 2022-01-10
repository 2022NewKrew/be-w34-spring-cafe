package com.kakao.cafe.application.dto;

public class UserAccountEnrollCommand {

    private final String password;
    private final String username;
    private final String email;

    public UserAccountEnrollCommand(String password, String username, String email) {
        this.password = password;
        this.username = username;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
