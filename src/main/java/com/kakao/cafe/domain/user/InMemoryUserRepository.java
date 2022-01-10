package com.kakao.cafe.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("userRepository")
public class InMemoryUserRepository implements UserRepository{

    private final List<User> users;

    @Autowired
    public InMemoryUserRepository(List<User> users) {
        this.users = users;
    }

    @Override
    public void create(User user) {
        users.add(user);
    }

    @Override
    public Optional<User> findById(String userId) {
        return users.stream().filter(e -> e.getUserId().equals(userId)).findAny();
    }

    @Override
    public List<User> findAll() {
        return users;
    }
}
