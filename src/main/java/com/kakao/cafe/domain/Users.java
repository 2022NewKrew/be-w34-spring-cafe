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

    public List<User> getList() {
        return Collections.unmodifiableList(list);
    }
}
