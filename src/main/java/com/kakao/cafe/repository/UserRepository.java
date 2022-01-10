package com.kakao.cafe.repository;

import com.kakao.cafe.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        data.add(user);
        return user;
    }

    public List<User> list() {
        return Collections.unmodifiableList(data);
    }

    public User get(String id) {
        return data.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
