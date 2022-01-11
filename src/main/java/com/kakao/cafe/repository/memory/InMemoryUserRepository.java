package com.kakao.cafe.repository.memory;

import com.kakao.cafe.entity.User;
import com.kakao.cafe.repository.UserRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryUserRepository implements UserRepository {
    private static final Map<Long, User> repository = new HashMap<>();
    private final AtomicLong count = new AtomicLong(0L);

    @Override
    public void saveUser(User user) {
        user.setId(count.getAndIncrement());
        repository.put(user.getId(), user);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        return Optional.ofNullable(repository.get(userId));
    }

    @Override
    public void updateUser(Long userId, String name, String email, String password) {
        Optional<User> optionalUser = findUserById(userId);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("유저를 찾을 수 없습니다.");
        }
        User user = optionalUser.get();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
    }
}
