package com.kakao.cafe.domain.user;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private static Map<Long, User> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    @Override
    public User add(User user) {
        user.setId(++sequence);
        store.put(user.getId(), user);
        return null;
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }
}
