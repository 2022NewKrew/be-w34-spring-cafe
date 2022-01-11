package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;
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
    public void save(User entity) {
        entity.setId(sequenceId.incrementAndGet());
        users.add(entity);
    }

    @Override
    public void update(User entity) {
        int index = users.indexOf(entity);
        users.set(index, entity);
    }
}
