package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserMemoryRepository implements UserRepository{

    private static List<User> userList = new ArrayList<>();

    @Override
    public User save(User user) {
        userList.add(user);
        return user;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userList.stream()
                .filter(user -> userId.equals(user.getUserId()))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return userList;
    }
}
