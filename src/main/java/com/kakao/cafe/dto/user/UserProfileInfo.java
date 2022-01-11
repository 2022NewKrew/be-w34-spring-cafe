package com.kakao.cafe.dto.user;

public class UserProfileInfo {
    private final String name;
    private final String email;

    public UserProfileInfo(String name, String email) {
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
