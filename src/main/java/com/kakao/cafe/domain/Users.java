package com.kakao.cafe.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Users {
    private static final List<User> list = new ArrayList<>();

    public void add(final User user) {
        list.add(Objects.requireNonNull(user));
    }

    public boolean checkIdExist(final String id) {
        Objects.requireNonNull(id);

        for (User u : list) {
            if (id.equals(u.getId())) {
                return true;
            }
        }
        return false;
    }

    public User find(final String id) {
        Objects.requireNonNull(id);
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
