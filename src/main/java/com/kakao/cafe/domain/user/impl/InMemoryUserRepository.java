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
    public Optional<User> findById(long id) {
        return Optional.of(store.get((int)id - 1));
    }

    @Override
    public List<User> findAll() {
        return store;
    }
}
