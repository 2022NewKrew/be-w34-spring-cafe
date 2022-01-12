package com.kakao.cafe.domain;

import java.util.Date;

public class User {
    private long id;
    private String email;
    private String name;
    private String password;
    private Date creationTime;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        if (!email.isBlank()) {
            this.email = email;
        }
    }

    public void setName(String name) {
        if (!name.isBlank()) {
            this.name = name;
        }
    }

    public void setPassword(String password) {
        if (!password.isBlank()) {
            this.password = password;
        }
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public long getId() {
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

    public Date getCreationTime() {
        return creationTime;
    }
}
