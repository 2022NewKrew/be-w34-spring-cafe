package com.kakao.cafe.model;

import com.kakao.cafe.exception.CustomException;
import com.kakao.cafe.exception.ErrorCode;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Users {

    private final List<User> users;

    public Users(List<User> users) {
        this.users = users;
    }

    public void add(User user) {
        if (isDuplicatedUserId(user)) {
            throw new CustomException(ErrorCode.USERID_DUPLICATION);
        }
        users.add(user);
    }

    private boolean isDuplicatedUserId(User requestedUser) {
        return users.stream()
                .anyMatch(user -> user.getUserId().equals(requestedUser.getUserId()));
    }

    public Optional<User> findById(UUID id) {
        return users.stream()
                .filter(user -> id.equals(user.getId()))
                .findFirst();
    }

    public Optional<User> findByUserId(String userId) {
        return users.stream()
                .filter(user -> userId.equals(user.getUserId()))
                .findFirst();
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
