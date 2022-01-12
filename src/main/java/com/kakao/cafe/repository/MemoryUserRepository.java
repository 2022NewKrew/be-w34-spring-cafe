package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;

import java.util.*;

public class MemoryUserRepository implements UserRepository {

    private static final Map<Long, User> userMap = new HashMap<>();
    private static Long idNumber = 0L;

    @Override
    public Long generateId() {
        return ++idNumber;
    }

    @Override
    public User signUp(User user) {
        userMap.put(user.getId(), user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(userMap.values());
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userMap.values().stream().filter(user -> user.getUserId().equals(userId)).findFirst();
    }
}
