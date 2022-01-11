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
    private final Map<String, String> nameToUserId;

    public UserRepositoryImpl() {
        this.users = new HashMap<>();
        this.nameToUserId = new HashMap<>();
    }

    @Override
    public void add(User user) {
        users.put(user.getUserId(), user);
        nameToUserId.put(user.getName(), user.getUserId());
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
        Optional<String> userId = Optional.ofNullable(nameToUserId.get(name));
        if (userId.isEmpty()) {
            return Optional.empty();
        }
        return findUserByUserId(userId.get());
    }
}
