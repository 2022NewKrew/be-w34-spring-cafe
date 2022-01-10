package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;

import java.util.ArrayList;
import java.util.List;

public class MemoryUserRepository implements UserRepository{
    private static final List<User> store = new ArrayList<>();
    @Override
    public User save(User user) {
        store.add(user);
        return user;
    }
}
