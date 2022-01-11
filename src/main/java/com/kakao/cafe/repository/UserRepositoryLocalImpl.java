package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepositoryLocalImpl implements UserRepository {
    private static final AtomicLong count = new AtomicLong(1);
    private final List<User> users = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void save(User user) {
        user.setId(count.getAndIncrement());
        user.setCreationTime(new Date());
        users.add(user);
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }
}
