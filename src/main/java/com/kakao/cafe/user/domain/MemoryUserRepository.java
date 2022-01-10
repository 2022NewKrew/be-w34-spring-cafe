package com.kakao.cafe.user.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryUserRepository implements UserRepository {

    private final Map<String, User> table;
    private final AtomicLong pk;

    public MemoryUserRepository() {
        table = new HashMap<>();
        pk = new AtomicLong();
    }

    @Override
    public User save(User user) {
        user.setId(nextId());
        table.put(user.getUsername(), user);
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(table.getOrDefault(username, null));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(table.values());
    }

    private Long nextId() {
        return pk.incrementAndGet();
    }
}
