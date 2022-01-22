package com.kakao.cafe.domain.user;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class User {
    private Long id;
    private String email;
    private String name;
    private String password;
    private LocalDateTime creationTime;

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreationTime(Timestamp timestamp) {
        creationTime = timestamp.toLocalDateTime();
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
