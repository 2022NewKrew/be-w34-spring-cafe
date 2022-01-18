package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Repository
@RequiredArgsConstructor
public class UserMemoryRepository implements UserRepository {

    private final static Map<Long, User> userMap = new TreeMap<>();

    @Override
    public void save(User user) {
        if (user.getId() != null) {
            userMap.put(user.getId(), user);
            return;
        }
        user.setId(userMap.size() + 1L);
        userMap.put(userMap.size() + 1L, user);
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userMap.values().stream()
                .filter(user -> userId.equals(user.getUserId()))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(userMap.values());
    }
}
