package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private static final List<User> users = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicLong idx = new AtomicLong(0);
    static {
        users.add(new User(0L, "aa@aa.com","멋진삼","aaaa"));
    }

    @Override
    public Long autoIncrement(){
        return idx.incrementAndGet();
    }

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.stream()
            .filter(user -> user.getEmail().equals(email))
            .findFirst();
    }

    @Override
    public Optional<User> findById(Long id) {
        return users.stream()
            .filter(user -> user.getId().equals(id))
            .findFirst();
    }
}
