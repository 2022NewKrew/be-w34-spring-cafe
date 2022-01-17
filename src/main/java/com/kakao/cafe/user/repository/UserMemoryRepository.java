package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserMemoryRepository implements UserRepository {
    private List<User> users;

    public UserMemoryRepository() {
        users = new ArrayList<>();
    }

    public void save(User user) {
        users.add(user);
    }

    public Optional<User> findByUserId(String userId) {
        return users.stream()
                .filter(e -> e.getUserId().equals(userId))
                .findAny();
    }

    public List<User> findAll() {
        return users;
    }


    @Override
    public void remove(User user) {
        users.remove(user);
    }

    @Override
    public void update(User user) {
    }
}
