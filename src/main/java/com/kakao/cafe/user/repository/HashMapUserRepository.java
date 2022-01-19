package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class HashMapUserRepository implements UserRepository {
    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong();

    public boolean isUserInDb(User user) {
        return user.getId() != null && isUserInDb(user.getId());
    }

    public boolean isUserInDb(Long id) {
        return users.containsKey(id);
    }

    @Override
    public Long add(User user) {
        if (isUserInDb(user)) {
            throw new RuntimeException("Duplicate primary key: " + user);
        }

        Long id = nextId.incrementAndGet();
        users.put(id, new User(id, user.getEmail(), user.getUsername(), user.getPassword(), user.getStatus(),
                               user.getDisplayName(), LocalDateTime.now(), LocalDateTime.now()));
        return id;
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<User> get(String username) {
        return users.entrySet().stream().filter(user -> user.getValue().getUsername().equals(username)).findAny().map(
                Map.Entry::getValue);
    }

    @Override
    public Optional<User> get(Long id) {
        return Optional.of(users.get(id));
    }

    @Override
    public void update(User user) {
        if (isUserInDb(user)) {
            throw new NoSuchElementException("Primary key not found: " + user);
        }

        users.put(user.getId(),
                  new User(user.getId(), user.getEmail(), user.getUsername(), user.getPassword(), user.getStatus(),
                           user.getDisplayName(), user.getCreatedAt(), LocalDateTime.now()));
    }

    @Override
    public void remove(Long id) {
        if (isUserInDb(id)) {
            throw new NoSuchElementException("Primary key not found: " + id);
        }

        users.remove(id);
    }
}
