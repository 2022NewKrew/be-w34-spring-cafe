package com.kakao.cafe.domain.user;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserInMemoryRepository implements UserRepository {
    private static final Map<String, User> users = Collections.synchronizedMap(new HashMap<>());

    @Override
    public Optional<User> findByUserId(String userId) {
        return Optional.of(users.get(userId));
    }

    @Override
    public void save(User user) {
        users.put(user.getUserId(), user);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}
