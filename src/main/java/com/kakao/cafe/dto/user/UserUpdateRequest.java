package com.kakao.cafe.dto.user;

public class UserUpdateRequest {

    private final String nickname;
    private final String email;
    private final String password;
    private final String newPassword;

    public UserUpdateRequest(String nickname, String email, String password, String newPassword) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.newPassword = newPassword;
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

    public String getNewPassword() {
        return newPassword;
    }
}
