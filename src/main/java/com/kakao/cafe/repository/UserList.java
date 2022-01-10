package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class UserList {
    private final ArrayList<User> userList;

    private UserList() {
        userList = new ArrayList<>();
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public boolean isDuplicated(User user) {
        return userList.stream()
                .anyMatch(eachUser -> eachUser.getUserId().equals(user.getUserId()));
    }

    public Optional<User> findUserByUserId(String userId) {
        return userList.stream()
                .filter(eachUser -> eachUser.getUserId().equals(userId))
                .findAny();
    }
}
