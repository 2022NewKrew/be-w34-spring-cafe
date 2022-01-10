package com.kakao.cafe.repository;

import com.kakao.cafe.model.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class UserRepository {

    private final List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> findUsers() {
        return Collections.unmodifiableList(users);
    }

    public Optional<User> findUserByUserId(String userId) {
        return users.stream()
            .filter(user -> user.getUserId().equals(userId))
            .findFirst();
    }
}
