package com.kakao.cafe.user.dto.request;

import org.springframework.lang.NonNull;

public class UserUpdateRequest {

    private final String passwordCheck;
    private final String newPassword;
    private final String name;
    private final String email;

    public UserUpdateRequest(String passwordCheck, String newPassword, String name, String email) {
        this.passwordCheck = passwordCheck;
        this.newPassword = newPassword;
        this.name = name;
        this.email = email;
    }

    public String getPasswordCheck() { return this.passwordCheck; }
    public String getNewPassword() { return this.newPassword; }
    public String getName() { return this.name; }
    public String getEmail() { return this.email; }
}
