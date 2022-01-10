package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserMemoryRepository implements UserRepository{

    private Map<String, User> userList = new HashMap<>();

    @Override
    public void save(User user) {
        String userId = user.getUserId();
        userList.put(userId, user);
    }

    @Override
    public List<User> findAll() {
        return userList.values().stream().collect(Collectors.toList());
    }

    @Override
    public User findById(String userId) {
        return userList.get(userId);
    }
}
