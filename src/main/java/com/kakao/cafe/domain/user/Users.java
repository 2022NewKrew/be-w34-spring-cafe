package com.kakao.cafe.domain.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Users {

    private final List<User> userList;

    public Users() {
        this.userList = Collections.synchronizedList(new ArrayList<>());
    }

    public List<User> getUserList() {
        return this.userList;
    }

    public void add(User user) {
        userList.add(user);
    }

    public boolean isUserDuplicated(User user) {
        synchronized (userList) {
            return userList.stream()
                    .anyMatch((existingUser) -> existingUser.getId().equals(user.getId()));
        }
    }

    public Optional<User> findByUserId(UserId userId) {
        synchronized (userList){
            return userList.stream()
                    .filter((user) -> user.getId().equals(userId))
                    .findAny();
        }
    }
}
