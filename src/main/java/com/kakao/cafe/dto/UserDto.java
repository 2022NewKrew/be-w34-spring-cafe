package com.kakao.cafe.dto;

public class UserDto {
    private String userId;
    private String email;
    private String name;
    private String password;

    public UserDto(String userId, String email, String name, String password) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
