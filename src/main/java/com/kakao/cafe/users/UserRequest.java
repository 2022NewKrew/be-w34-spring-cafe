package com.kakao.cafe.users;

public class UserRequest {

    private final String userId;

    private final String password;

    private final String updatePassword;

    private final String name;

    private final String email;

    public UserRequest(String userId, String password, String updatePassword, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.updatePassword = updatePassword;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getUpdatePassword() {
        return updatePassword;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
