package com.kakao.cafe.user;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Database를 사용하면 안되어 HashMap으로 데이터베이스를 구성합니다.
 */
@Repository
public class UserRepositoryHash implements UserRepository {

    private static Map<Long, User> inMemoryDatabase = new HashMap<>();
    private static long autoIncrementNumber = 0L;

    @Override
    public Long save(User user) {
        user.setId(autoIncrementNumber);
        inMemoryDatabase.put(autoIncrementNumber, user);
        return autoIncrementNumber++;
    }

    @Override
    public User findOne(Long id) {
        return inMemoryDatabase.get(id);
    }

    @Override
    public List<User> findAll() {
        // TODO stream 으로 변경
        List<User> users = new ArrayList<>();
        List<Long> keys = new ArrayList<>(inMemoryDatabase.keySet());

        for (Long key : keys) {
            users.add(inMemoryDatabase.get(key));
        }

        return users;
    }
}
