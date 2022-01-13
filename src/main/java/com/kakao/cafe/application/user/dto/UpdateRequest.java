package com.kakao.cafe.application.user.dto;

public class UpdateRequest {

    private final String name;
    private final String email;

    public UpdateRequest(String name, String email) {
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
