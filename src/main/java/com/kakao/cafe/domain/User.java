package com.kakao.cafe.domain;

import lombok.Getter;

@Getter
public class User {

    private final Long uid;
    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public User(String userId, String password, String name, String email) {
        uid = 0L;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public void validate() {
        validatePassword();
    }

    private void validatePassword() {
        if (password.length() < 6) {
            throw new IllegalArgumentException("[ERROR] 비밀번호는 최소 6자 이상이어야 합니다.");
        }
    }

    @Override
    public String toString() {
        return "User{" +
            "userId='" + userId + '\'' +
            ", password='" + password + '\'' +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
