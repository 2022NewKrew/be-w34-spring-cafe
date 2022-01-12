package com.kakao.cafe.user.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    public static final List<User> currentUsers = new ArrayList<>();

    public void save(User user) {
        currentUsers.add(user);
    }

    public List<User> findAll() {
        return currentUsers;
    }

    public User findByIdOrNull(String userId) {
        return currentUsers.stream()
                .filter(user -> user.isSameUser(userId))
                .findFirst().orElse(null);
    }
}
