package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;

import java.util.*;

public class MemoryUserRepository implements UserRepository {

    private final Map<Long, User> userMap = new HashMap<>();
    private Long idNumber = 0L;

    @Override
    public Long generateId() {
        return ++idNumber;
    }

    @Override
    public void create(User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(userMap.values());
    }

    @Override
    public User findByUserId(String userId) {
        Optional<User> result = userMap.values().stream().filter(user -> user.getUserId().equals(userId)).findFirst();
        if (result.isPresent()) {
            return result.get();
        }
        throw new RuntimeException("해당하는 사용자 ID가 존재하지 않습니다.");
    }

    @Override
    public User findByIDPW(String userId, String password) {
        Optional<User> result = userMap.values().stream().filter(user -> user.getUserId().equals(userId) && user.getPassword().equals(password)).findFirst();
        if (result.isPresent()) {
            return result.get();
        }
        throw new RuntimeException("입력한 ID, PW 와 일치하는 사용자가 존재하지 않습니다.");
    }
}
