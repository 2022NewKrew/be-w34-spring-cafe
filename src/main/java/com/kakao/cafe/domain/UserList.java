package com.kakao.cafe.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserList {
    private final List<User> userList = new ArrayList<>();

    public UserList() {}

    public void addUser(User user) {
        userList.add(new User(user));
    }

    public User getUser(String userId) {
        return userList.stream()
                       .filter(user -> user.getUserId().equals(userId))
                       .collect(Collectors.toList()).get(0);
    }

    public List<User> getUserList() {
        return this.userList;
    }
}

