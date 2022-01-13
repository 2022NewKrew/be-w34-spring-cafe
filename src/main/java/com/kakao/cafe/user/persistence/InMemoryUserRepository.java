package com.kakao.cafe.user.persistence;

import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> idToUser = new ConcurrentHashMap<>();
    private final List<User> userList = new CopyOnWriteArrayList<>();

    @Override
    public Optional<User> getUser(String userId) {
        return Optional.ofNullable(idToUser.getOrDefault(userId, null));
    }

    @Override
    public List<User> getAllUsers() {
        return Collections.unmodifiableList(userList);
    }

    @Override
    public void save(User user) {
        idToUser.put(user.getUserId(), user);
        userList.add(user);
    }
}
