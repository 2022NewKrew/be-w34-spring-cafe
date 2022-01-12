package com.kakao.cafe.infrastructure.user;

import com.kakao.cafe.domain.user.FindUserPort;
import com.kakao.cafe.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FindUserInMemoryAdapter implements FindUserPort {
    static final Map<String, User> users = Collections.synchronizedMap(new HashMap<>());

    @Override
    public Optional<User> findByUserId(String userId) {
        return Optional.ofNullable(users.get(userId));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<User> findByUserIdAndPassword(String userId, String password) {
        User userById = users.get(userId);
        if (userById.getPassword().equals(password) == false) {
            return Optional.empty();
        }

        return Optional.of(userById);
    }
}
