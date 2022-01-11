package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;

import java.util.*;

public class UserMemoryRepository implements UserRepository{
    private static Map<Long, User> userList = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public User save(User user) {
        user.setId(++sequence);
        userList.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userList.get(id));
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userList.values().stream()
                .filter(user -> userId.equals(user.getUserId()))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userList.values());
    }
}