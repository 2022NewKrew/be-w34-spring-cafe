package com.kakao.cafe.user.dao;

import com.kakao.cafe.common.Dao;
import com.kakao.cafe.user.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

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

    public User fetchByUserId(String userId) {
        for (Map.Entry<Long, User> entry : map.entrySet()) {
            if (entry.getValue().getUserId().equals(userId)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
