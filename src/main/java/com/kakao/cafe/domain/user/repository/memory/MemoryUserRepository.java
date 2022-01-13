package com.kakao.cafe.domain.user.repository.memory;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.repository.UserRepository;

import java.util.*;

//@Repository
public class MemoryUserRepository implements UserRepository {

    private static Map<Long, User> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public User save(User user) {
        user.setId(++sequence);
        store.put(sequence, user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return store.values().stream()
                .filter(user -> user.getUserId().equals(userId))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<User> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public User update(User user) {
        store.put(user.getId(), user);
        return user;
    }

}
