package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepositoryLocalImpl implements UserRepository {
    private static final AtomicLong count = new AtomicLong(1);
    private final List<User> users = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void save(User user) {
        if (user.getId() == null) {
            user.setId(count.getAndIncrement());
            user.setCreationTime(LocalDateTime.now());
            users.add(user);
        }
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(Long id) throws NotFoundException {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("해당 아이디의 사용자가 없습니다."));
    }

    @Override
    public User findByEmail(String email) throws NotFoundException {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("해당 이메일의 사용자가 없습니다."));
    }
}
