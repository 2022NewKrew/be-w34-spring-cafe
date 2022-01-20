package com.kakao.cafe.domain;

import lombok.Getter;

@Getter
public class UserDto {

    private final String userId;
    private String password;
    private String name;
    private String email;

    public UserDto(String userId, String password, String name, String email) {
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

    public UserNoPassword userNoPassword() {
        return new UserNoPassword(userId, name, email);
    }

    public static class UserNoPassword {

        private final String userId;
        private final String name;
        private final String email;

        private UserNoPassword(String userId, String name, String email) {
            this.userId = userId;
            this.name = name;
            this.email = email;
        }
    }
}
