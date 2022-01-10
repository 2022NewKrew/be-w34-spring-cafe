package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryUserRepository implements UserRepository{
    private static final List<User> store = new ArrayList<>();
    private static long sequence = 0L;
    @Override
    public User save(User user) {
        store.add(user);
        user.setId(++sequence);
        return user;
    }

    @Override
    public List<User> findAll() {
        return store;
    }

    @Override
    public User findByUserId(String userId) {
        return store.stream().
                filter(u -> u.getUserId().equals(userId)).
                findFirst().
                orElseThrow(() -> new IllegalArgumentException());
    }
}
