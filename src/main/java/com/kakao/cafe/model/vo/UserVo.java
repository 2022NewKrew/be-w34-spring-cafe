package com.kakao.cafe.model.vo;

import java.util.Objects;

public class UserVo {

    private int id;
    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public UserVo(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public UserVo(int id, String userId, String password, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVo userVo = (UserVo) o;
        return id == userVo.id && Objects.equals(userId, userVo.userId) && Objects.equals(password, userVo.password) && Objects.equals(name, userVo.name) && Objects.equals(email, userVo.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, password, name, email);
    }
}
