package com.kakao.cafe.domain.user;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("userRepository")
public class InMemoryUserRepository implements UserRepository{

    private final Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getUserId(), user);
    }

    @Override
    public User findById(String userId) {
        return users.get(userId);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}
