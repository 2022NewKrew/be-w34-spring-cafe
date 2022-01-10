package com.kakao.cafe.api.dto;

import com.kakao.cafe.application.dto.UserAccountEnrollCommand;

public class UserAccountEnrollRequest {

    private final String email;
    private final String username;
    private final String password;

    public UserAccountEnrollRequest(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UserAccountEnrollCommand toCommand() {
        return new UserAccountEnrollCommand(
                password,
                username,
                email
        );
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
