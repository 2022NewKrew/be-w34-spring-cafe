package com.kakao.cafe.repository.user;

import com.kakao.cafe.exception.CustomException;
import com.kakao.cafe.exception.ErrorCode;
import com.kakao.cafe.model.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

class Users {

    private final List<User> users;

    public Users() {
        this.users = Collections.synchronizedList(new ArrayList<>());
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
        Optional<User> result = users.stream()
                .filter(user -> id.equals(user.getId()))
                .findFirst();

        return result.isPresent() ? Optional.of(User.copy(result.get())) : Optional.empty();
    }

    public Optional<User> findByUserId(String userId) {
        Optional<User> result = users.stream()
                .filter(user -> userId.equals(user.getUserId()))
                .findFirst();

        return result.isPresent() ? Optional.of(User.copy(result.get())) : Optional.empty();
    }

    public List<User> getUsers() {
        return users.stream()
                .map(User::copy)
                .collect(Collectors.toUnmodifiableList());
    }
}
