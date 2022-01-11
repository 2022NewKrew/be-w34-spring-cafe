package com.kakao.cafe.repository;

import com.kakao.cafe.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryUserRepository implements UserRepository {

    private static final List<User> userList = new ArrayList<>();

    @Override
    public User save(User user) {
        userList.add(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return userList;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userList.stream()
                .filter(user -> userId.equals(user.getUserId()))
                .findFirst();
    }

    @Override
    public Optional<User> findByName(String name) {
        return userList.stream()
                .filter(user -> name.equals(user.getName()))
                .findFirst();
    }
}
