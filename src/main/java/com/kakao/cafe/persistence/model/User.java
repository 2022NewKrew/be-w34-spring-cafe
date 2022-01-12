package com.kakao.cafe.persistence.model;

import java.time.LocalDateTime;
import lombok.ToString;

@ToString(exclude = "password")
public class User {

    private final Long id;

    private final String uid;
    private final String password;
    private final String name;
    private final String email;

    private final LocalDateTime createdAt;

    public static User of(String uid, String password, String name, String email) {
        return new User(null, uid, password, name, email, LocalDateTime.now());
    }

    public static User of(Long id, String uid, String password, String name, String email,
        LocalDateTime createdAt) {
        return new User(id, uid, password, name, email, createdAt);
    }

    private User(Long id, String uid, String password, String name, String email,
        LocalDateTime createdAt) {
        this.id = id;
        this.uid = uid;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean validPassword(String password) {
        return this.password.equals(password);
    }
}
