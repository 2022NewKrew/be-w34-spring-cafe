package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.Password;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserMemoryRepository implements UserRepository {
    private List<User> users;

    public UserMemoryRepository() {
        users = new ArrayList<>();
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public Optional<User> findByUserId(UserId userId) {
        return users.stream()
                .filter(e -> e.getUserId().equals(userId))
                .findAny();
    }

    @Override
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

    @Override
    public Optional<User> findByUserIdAndPassword(UserId userId, Password password) {
        return Optional.empty();
    }
}
