package com.kakao.cafe.user.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class User {
    private final UserId userId;
    private Password password;
    private Name name;
    private Email email;

    public String getUserId() {
        return userId.getUserId();
    }

    public String getPassword() {
        return password.getPassword();
    }

    public String getName() {
        return name.getName();
    }

    public String getEmail() {
        return email.getEmail();
    }
}
