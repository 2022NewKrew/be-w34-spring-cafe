package com.kakao.cafe.repository;

import com.kakao.cafe.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final List<User> data = new ArrayList<>();
    {
        data.add(
                new User.Builder()
                        .id("test")
                        .email("test@example.com")
                        .password("1234")
                        .name("test")
                        .build()
        );
    }

    public User create(User user) {
        User existing = find(user.getId()).orElse(null);
        if (existing != null) {
            return null;
        }
        data.add(user);
        return user;
    }

    public List<User> list() {
        return Collections.unmodifiableList(data);
    }

    public User get(String id) {
        return find(id).orElse(null);
    }

    public User login(String id, String password) {
        Optional<User> found = find(id);
        if (found.isEmpty()) {
            return null;
        }
        User user = found.get();
        boolean ok = user.getPassword().equals(password);
        if (!ok) {
            return null;
        }
        return user;
    }

    private Optional<User> find(String id) {
        return data.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }
}
