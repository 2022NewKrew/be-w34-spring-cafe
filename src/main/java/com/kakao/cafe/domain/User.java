package com.kakao.cafe.domain;

import lombok.Getter;

@Getter
public class User {

    private final String id;
    private final String username;
    private String password;
    private String name;
    private String email;

    public User(String id, String username, String password, String name, String email) {
        this.id       = id;
        this.username = username;
        this.password = password;
        this.name     = name;
        this.email    = email;
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
        setPassword(password);
    }

    public void setPassword(String password) {
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
            "id='" + id + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
