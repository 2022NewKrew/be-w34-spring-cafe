package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryUserRepository implements UserRepository {

    private static final List<User> userList = new ArrayList<>();
    private static Long idNumber = 0L;

    @Override
    public Long generateId() {
        return ++idNumber;
    }

    @Override
    public User signUp(User user) {
        user.setId(++idNumber);
        userList.add(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(userList);
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userList.stream().filter(user -> user.getUserId().equals(userId)).findFirst();
    }
}
