package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository implements RepositoryInterface<User> {
    private static final List<User> userList = new ArrayList<>();
    private static final AtomicLong sequence = new AtomicLong();

    @Override
    public void save(User user) {
        user.setUserId(sequence.incrementAndGet());
        userList.add(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userList.stream()
                .filter(user -> user.getUserId().equals(id))
                .findAny();
    }

    @Override
    public Optional<User> findByName(String name) {
        return userList.stream()
                .filter(user -> user.getName().equals(name))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return userList;
    }
}
