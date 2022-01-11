package com.kakao.cafe.domain;

import java.util.Date;

public class User {
    private long id;
    private final String email;
    private final String name;
    private final String password;
    private Date creationTime;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
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
