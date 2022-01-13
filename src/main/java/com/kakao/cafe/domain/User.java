package com.kakao.cafe.domain;

import lombok.Getter;

@Getter
public class User {
    private final String id;
    private final String password;
    private final String name;
    private final String email;

    public User(String id, String password, String name, String email) {
        validate(id, password, name, email);
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    private void validate(String id, String password, String name, String email) {
        validateId(id);
        validatePassword(password);
        validateName(name);
        validateEmail(email);
        return;
    }

    private void validateId(String id) {
        return;
    }

    private void validatePassword(String password) {
        return;
    }

    private void validateName(String name) {
        return;
    }

    private void validateEmail(String email) {
        return;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
