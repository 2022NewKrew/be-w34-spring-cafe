package com.kakao.cafe.users;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemoryUserRepository implements UserRepository {
    private final List<User> users;

    public MemoryUserRepository() {
        users = new ArrayList<>();
    }

    @Override
    public User save(User user) {
        users.add(user);

        return user;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return users;
    }
}
