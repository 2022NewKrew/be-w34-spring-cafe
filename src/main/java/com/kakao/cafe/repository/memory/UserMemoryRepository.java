package com.kakao.cafe.repository.memory;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.repository.dto.UserResult;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

//@Repository
public class UserMemoryRepository implements UserRepository {

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
    public Optional<User> findById(String userId) {
        return Optional.of(userList.get(userId));
    }

    @Override
    public void update(User user) {

    }
}
