package com.kakao.cafe.domain.repository.user;

import com.kakao.cafe.domain.entity.User;

import java.util.*;

public class MemoryUserRepository implements UserRepository {

    private static final Map<Long, User> store = new Hashtable<>();
    private static long sequence = 0L;

    @Override
    public void save(User user) {
        user.setId(++sequence);
        store.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return store.values().stream()
                .filter(user -> user.getName().equals(userId))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }


    @Override
    public long countRecords() {
        return store.size();
    }
}
