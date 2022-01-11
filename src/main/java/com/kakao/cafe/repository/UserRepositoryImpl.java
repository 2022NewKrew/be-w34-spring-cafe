package com.kakao.cafe.repository;

import com.kakao.cafe.model.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final Map<String, User> users;

    public UserRepositoryImpl() {
        this.users = new HashMap<>();
    }

    @Override
    public void add(User user) {
        users.put(user.getUserId(), user);
    }

    @Override
    public List<User> findAllUsers() {
        return users.values().stream()
            .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Optional<User> findUserByUserId(String userId) {
        return Optional.ofNullable(users.get(userId));
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return users.values().stream()
            .filter(user -> user.getName().equals(name))
            .findFirst();
    }
}
