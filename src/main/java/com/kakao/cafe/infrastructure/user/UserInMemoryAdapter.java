package com.kakao.cafe.infrastructure.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserPort;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserInMemoryAdapter implements UserPort {
    private static final Map<String, User> users = Collections.synchronizedMap(new HashMap<>());

    @Override
    public Optional<User> findByUserId(String userId) {
        return Optional.ofNullable(users.get(userId));
    }

    @Override
    public void save(User user) {
        users.put(user.getUserId(), user);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}
