package com.kakao.cafe.user.domain;

import java.util.ArrayList;
import java.util.List;

public class Users {

    private final List<User> users;

    public Users(List<User> users) {
        this.users = new ArrayList<>(users);
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }
}
