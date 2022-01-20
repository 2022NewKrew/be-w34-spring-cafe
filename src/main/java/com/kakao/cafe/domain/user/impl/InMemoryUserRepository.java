package com.kakao.cafe.domain.user.impl;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryUserRepository implements UserRepository {
    private static final List<User> store = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicLong sequence = new AtomicLong(1L);

    @Override
    public void add(User user) {
        user.setId(sequence.getAndIncrement());
        store.add(user);
    }

    @Override
    public void update(User user) {
        store.stream()
                .filter(x -> Objects.equals(x.getId(), user.getId()))
                .findAny()
                .ifPresent(x -> {
                    x.setEmail(user.getEmail());
                    x.setNickname(user.getNickname());
                    x.setPassword(user.getPassword());
                });
    }

    @Override
    public Optional<User> findById(long id) {
        if (id > store.size()) {
            return Optional.empty();
        }

        return Optional.of(store.get((int)id - 1));
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return store.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findAny();
    }

    @Override
    public Optional<User> findByUserIdAndPassword(String userId, String password) {
        return store.stream()
                .filter(user -> user.getUserId().equals(userId))
                .filter(user -> user.getPassword().equals(password))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return store;
    }
}
