package com.kakao.cafe.web.user.dto;

public class UserUpdateRequest {
    private final String password;
    private final String name;
    private final String email;

    public UserUpdateRequest(String password, String name, String email) {
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
