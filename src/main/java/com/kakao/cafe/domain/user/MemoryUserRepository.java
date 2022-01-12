package com.kakao.cafe.domain.user;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryUserRepository implements UserRepository {
    private static final Map<String, User> userStore = new HashMap<>();

    @Override
    public User save(User user) {
        user.setId((long) userStore.size() + 1);
        userStore.put(user.getUserId(), user);

        return userStore.get(user.getUserId());
    }

    @Override
    public User edit(String userId, User user) {
        user.setId(userStore.get(userId).getId());
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
}
