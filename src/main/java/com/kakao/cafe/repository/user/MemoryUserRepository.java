package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryUserRepository implements UserRepository {

    private Map<Long, User> userMap = new TreeMap<>();
    private AtomicLong key = new AtomicLong(1L);

    @Override
    public Long insert(User user) {
        long currentId = key.getAndIncrement();
        user.updateId(currentId);
        userMap.put(currentId, user);
        return user.getId();
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(userMap.values());
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userMap.entrySet().stream()
                .filter(entry -> entry.getValue().getUserId().equals(userId))
                .map(Map.Entry::getValue)
                .findFirst();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userMap.getOrDefault(id, null));
    }

    @Override
    public Long update(User updateUser) {
        long id = updateUser.getId();
        userMap.put(id, updateUser);
        return id;
    }
}
