package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

@Repository
public class SimpleUserRepository implements UserRepository {

    private final List<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        synchronized (users) {
            if (isDuplicated(user.getUserId())) {
                throw new DuplicateKeyException("userId 중복");
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
    public User findByUserId(String userId) {
        return users.stream()
            .filter(user -> user.getUserId().equals(userId))
            .findFirst()
            .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void deleteAll() {
        users.clear();
    }
}
