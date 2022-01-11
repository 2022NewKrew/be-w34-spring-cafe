package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserMemoryRepository implements UserRepository {
    private List<User> users;

    public UserMemoryRepository() {
        users = new ArrayList<>();
    }

    public void save(User user) {
        users.add(user);
    }

    public Optional<User> findByUserId(String userId) {
        Optional<User> user = users.stream()
                .filter(e -> e.getUserId().equals(userId))
                .findAny();
        return user;
    }

    public List<User> findAll() {
        return users;
    }
}
