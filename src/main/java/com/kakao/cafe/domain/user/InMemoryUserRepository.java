package com.kakao.cafe.domain.user;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("userRepository")
public class InMemoryUserRepository implements UserRepository{

    private final Map<String, User> users = new HashMap<>();

    @Override
    public void create(User user) {
        users.put(user.getUserId(), user);
    }

    @Override
    public Optional<User> findById(String userId) {
        return Optional.ofNullable(users.get(userId));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}
