package com.kakao.cafe.user.infra;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    public static final List<User> currentUsers = new ArrayList<>();

    @Override
    public User save(User user) {
        currentUsers.add(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return currentUsers;
    }

    @Override
    public User findByIdOrNull(String userId) {
        return currentUsers.stream()
                .filter(user -> user.isSameUserById(userId))
                .findFirst().orElse(null);
    }

    @Override
    public boolean existsById(String userId) {
        return currentUsers.stream().anyMatch(user -> user.isSameUserById(userId));
    }

    @Override
    public User findByUserNameOrNull(String userName) {
        return currentUsers.stream()
                .filter(user -> user.isSameUserByName(userName))
                .findFirst().orElse(null);
    }
}
