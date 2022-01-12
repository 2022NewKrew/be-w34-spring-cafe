package com.kakao.cafe.persistence.repository;

import com.kakao.cafe.persistence.model.User;
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
        users.put(user.getUid(), user);
    }

    @Override
    public List<User> findAllUsers() {
        return users.values().stream()
            .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Optional<User> findUserByUid(String uid) {
        return Optional.ofNullable(users.get(uid));
    }
}
