package com.kakao.cafe.domain.model;

import com.kakao.cafe.domain.dto.UserSignUpDTO;
import com.kakao.cafe.domain.dto.UserViewDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Users {
    private final Map<String, User> users = new HashMap<>();

    public void addUser(UserSignUpDTO userSignUpDTO) {
        validateDuplicateUserId(userSignUpDTO.getUserId());

        User user = new User(userSignUpDTO);

        users.put(user.getUserId(), user);
    }

    public List<User> getAllUsers() {
        return users.values().stream().collect(Collectors.toUnmodifiableList());
    }

    public User findUserById(String userId) {
        return Optional.ofNullable(users.get(userId)).orElseThrow(IllegalAccessError::new);
    }

    private void validateDuplicateUserId(String userId) {
        if (users.containsKey(userId)) {
            throw new IllegalArgumentException();
        }
    }

}
