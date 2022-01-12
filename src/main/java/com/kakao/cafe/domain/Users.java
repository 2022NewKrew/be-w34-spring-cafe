package com.kakao.cafe.domain;

import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Users {
    private static final List<User> list = new ArrayList<>();

    public void add(@NonNull final User user) {
        list.add(user);
    }

    public boolean checkIdExist(@NonNull final String id) {
        for (User u : list) {
            if (id.equals(u.getId())) {
                return true;
            }
        }
        return false;
    }

    public User findById(@NonNull final String id) {
        if (User.NONE.getId().equals(id)) {
            return User.NONE;
        }

        for (User u : list) {
            if (id.equals(u.getId())) {
                return u;
            }
        }
        return User.NONE;
    }

    public List<User> getList() {
        return Collections.unmodifiableList(list);
    }
}
