package com.kakao.cafe.domain.user;

import java.util.*;


public class MemoryUserRepository implements UserRepository {
    private static final Map<String, User> userStore = new HashMap<>();

    @Override
    public void insert(User user) {
        userStore.put(user.getUserId(), user);
    }

    @Override
    public User update(String userId, User user) {
        userStore.put(userId, user);
        return user;
    }

    @Override
    public Optional<User> findById(String userId) {
        return Optional.ofNullable(userStore.get(userId));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userStore.values());
    }

    public void clear() {
        userStore.clear();
    }
}
