package com.kakao.cafe.model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Users {

    private final List<User> users;

    public Users(List<User> users) {
        this.users = users;
    }

    public void add(User user) {
        if (isDuplicatedUserId(user)) {
            throw new IllegalArgumentException("중복된 아이디입니다.");
        }
        users.add(user);
    }

    private boolean isDuplicatedUserId(User requestedUser) {
        return users.stream()
                .anyMatch(user -> user.getUserId().equals(requestedUser.getUserId()));
    }

    public Optional<User> findByUserId(String userId) {
        return users.stream()
                .filter(user -> userId.equals(user.getUserId()))
                .findFirst();
    }

    public Optional<User> findByName(String name) {
        return users.stream()
                .filter(user -> name.equals(user.getName()))
                .findFirst();
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
