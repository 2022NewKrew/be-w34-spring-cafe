package com.kakao.cafe.user.domain;

import java.util.UUID;
import lombok.Getter;

@Getter
public class User {

    private final String uid;
    private final String userId;
    private String password;
    private String name;
    private String email;

    public User(String userId, String password, String name, String email) {
        this.uid = UUID.randomUUID().toString();
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public void validate() {
        validatePassword(password);
    }

    private void validatePassword(String password) {
        if (password.length() < 6) {
            throw new IllegalArgumentException("[ERROR] 비밀번호는 최소 6자 이상이어야 합니다.");
        }
    }

    public UserNoPassword userNoPassword() {
        return new UserNoPassword(uid, userId, name, email);
    }

    public void validateAndSetPassword(String password) {
        validatePassword(password);
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public static class UserNoPassword {

        private final String uid;
        private final String userId;
        private final String name;
        private final String email;

        private UserNoPassword(String uid, String userId, String name, String email) {
            this.uid = uid;
            this.userId = userId;
            this.name = name;
            this.email = email;
        }
    }
}
