package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository implements MyRepository<User, Long> {

    private final List<User> users = new CopyOnWriteArrayList<>();
    private final AtomicLong sequenceId = new AtomicLong();

    @Override
    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(u -> u.isEqualUserId(id))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User save(User entity) {
        entity = entity.setId(sequenceId.incrementAndGet());
        users.add(entity);
        return entity;
    }

    @Override
    public User update(User entity) {
        return null;
    }
}
