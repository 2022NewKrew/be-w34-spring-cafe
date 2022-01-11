package com.kakao.cafe.dao.user;

import com.kakao.cafe.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VolatilityUserStorage implements UserDao {
    private final List<User> users;

    public VolatilityUserStorage() {
        this.users = new ArrayList<>();
    }

    @Override
    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public User findUserByUserId(String userId) {
        return users
                .stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("찾는 사용자가 없습니다!"));
    }

    @Override
    public int getSize() {
        return users.size();
    }
}
