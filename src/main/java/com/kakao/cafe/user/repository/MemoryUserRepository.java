package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryUserRepository implements UserRepository {
    private static Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;

    public User save(User user) {
        user.setId(++sequence);
        store.put(user.getId(), user);
        return user;
    }

    public User findById(Long id) {
        return store.get(id);
    }

    public User findByUserId(String userId) {
        List<User> userList = new ArrayList<>(store.values());
        for (User user : userList) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }

        return null;
    }

    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }
}
