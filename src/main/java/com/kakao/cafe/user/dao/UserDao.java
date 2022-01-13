package com.kakao.cafe.user.dao;

import com.kakao.cafe.common.Dao;
import com.kakao.cafe.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class UserDao implements Dao<User> {
    private static final AtomicLong ID_COUNTER = new AtomicLong();
    private final Map<Long, User> map = new HashMap<>();

    @Override
    public User fetch(long id) {
        return map.get(id);
    }

    @Override
    public List<User> fetchAll() {
        return map.values().stream()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public long save(User target) {
        long id = ID_COUNTER.addAndGet(1L);
        map.put(id, target);
        return id;
    }

    public Optional<User> fetchByUserId(String userId) {
        return map.values()
                .stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst();
    }

    public int getUserCount() {
        return map.size();
    }
}
