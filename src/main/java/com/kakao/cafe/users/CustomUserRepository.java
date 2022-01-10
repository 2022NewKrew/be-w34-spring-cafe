package com.kakao.cafe.users;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;

@Primary
@Repository
public class CustomUserRepository implements UserRepository {

    private Map<String, User> userMap;

    public CustomUserRepository() {
        userMap = new HashMap<>();
    }

    @Override
    public void update(User user) {
        if (userMap.containsKey(user.getUserId())) throw new IllegalArgumentException();
        userMap.put(user.getUserId(), user);
    }

    @Override
    public Optional<User> findById(String id) {
        System.out.println("TODO");
        return Optional.of(null);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        System.out.println("TODO");
        return Optional.of(null);
    }

}
