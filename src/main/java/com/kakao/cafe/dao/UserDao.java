package com.kakao.cafe.dao;

import lombok.Getter;

@Getter
public class UserDao {
    private final long index;
    private final String id;
    private final String password;
    private final String name;
    private final String email;

    public UserDao(long index, String id, String password, String name, String email) {
        validate(index, id, password, name, email);
        this.index = index;
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    private void validate(long index, String id, String password, String name, String email) {
    }

    @Override
    public String toString() {
        return "UserDao{" +
                "index=" + index +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
