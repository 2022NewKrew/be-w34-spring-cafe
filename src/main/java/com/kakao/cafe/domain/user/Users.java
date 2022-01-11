package com.kakao.cafe.domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Users {

    private final List<User> userList;

    public Users() {
        this.userList = new ArrayList<>();
    }

    public List<User> getUserList() {
        return this.userList;
    }

    public void add(User user) {
        userList.add(user);
    }

    public boolean isUserDuplicated(User user) {
        return userList.stream()
                .anyMatch((existingUser) -> existingUser.getId().equals(user.getId()));
    }

    public Optional<User> findByUserId(UserId userId) {
        return userList.stream()
                .filter((user) -> user.getId().equals(userId))
                .findAny();
    }
}
