package com.kakao.cafe.web.repository.user;

import com.kakao.cafe.web.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryUserRepository implements UserRepository {

    private static List<User> users = new ArrayList<>();
    private static Long sequence = 0L;

    @Override
    public void save(User user) {
        user.setId(++sequence);
        users.add(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return users.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    public void clearStore() {
        users.clear();
    }
}
