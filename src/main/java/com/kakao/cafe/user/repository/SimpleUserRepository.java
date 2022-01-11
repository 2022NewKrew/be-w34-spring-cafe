package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class SimpleUserRepository implements UserRepository {

    private final List<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        synchronized (users) {
            if (isDuplicated(user.getUserId())) {
                throw new NoSuchElementException("존재하지 않는 userId 입니다.");
            }
            users.add(user);
        }
    }

    private boolean isDuplicated(String userId) {
        return users.stream()
            .map(User::getUserId)
            .anyMatch(id -> id.equals(userId));
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return users.stream()
            .filter(user -> user.getUserId().equals(userId))
            .findFirst();
    }
}
