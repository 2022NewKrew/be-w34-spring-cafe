package com.kakao.cafe.users;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class CustomUserRepository implements UserRepository {

    private final Map<String, User> userMap;

    public CustomUserRepository() {
        userMap = new HashMap<>();
    }

    @Override
    public void save(User user) {
        if (userMap.containsKey(user.getId())) throw new IllegalArgumentException();
        userMap.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.of(userMap.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        System.out.println("TODO");
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

}
