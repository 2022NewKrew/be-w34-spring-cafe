package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryUserRepository implements UserRepository{

    private static final Map<Long,User> userStore = new HashMap<>();


    @Override
    public void save(User user) {
        userStore.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userStore.get(id));
    }

    @Override
    public Optional<User> findByName(String name) {
        return userStore.values().stream()
                .filter(user -> user.getName().equals(name))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userStore.values());
    }
}
