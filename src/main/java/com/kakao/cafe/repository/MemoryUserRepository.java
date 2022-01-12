package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryUserRepository implements UserRepository{
    private static final List<User> store = Collections.synchronizedList(new ArrayList<User>());

    private static final int FIRST_INDEX = 1;

    private final AtomicLong atomicLong = new AtomicLong(FIRST_INDEX);
    @Override
    public User save(User user) {
        user.setId(atomicLong.getAndIncrement());
        store.add(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return store;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return store.stream()
                .filter(member -> member.getUserId().equals(userId))
                .findAny();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id.intValue() - 1));
    }

    @Override
    public void updateUser(Long id, User updateUser) {
        store.set(id.intValue()-1, updateUser);
    }
}
