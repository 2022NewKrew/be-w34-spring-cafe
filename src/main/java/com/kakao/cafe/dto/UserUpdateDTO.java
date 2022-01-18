package com.kakao.cafe.dto;

public class UserUpdateDTO {
    private final String userId;
    private final String password;
    private final String passwordCheck;
    private final String name;
    private final String email;

    public static UserUpdateDTO of(String userId, String password, String passwordCheck, String name, String email) {
        return new UserUpdateDTO(userId, password, passwordCheck, name, email);
    }

    private UserUpdateDTO(String userId, String password, String passwordCheck, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
