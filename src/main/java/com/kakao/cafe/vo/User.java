package com.kakao.cafe.vo;

import java.util.Objects;

public class User {
    private final Long id;
    private final String userId;
    private final String time;
    private final String password;
    private final String email;

    public User(Long id, String userId, String password, String email, String time) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }


    public String getUserId() {
        return userId;
    }


    public String getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
