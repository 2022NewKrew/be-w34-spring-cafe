package com.kakao.cafe.web.dto;

public class UserUpdateRequest {
    private final String name;
    private final String email;

    public UserUpdateRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
