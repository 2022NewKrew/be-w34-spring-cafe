package com.kakao.cafe.domain.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Users {

    private final List<User> userList;

    public Users() {
        this.userList = Collections.synchronizedList(new ArrayList<>());
    }

    public List<User> getUserList() {
        return this.userList;
    }

    public void add(User user) {
        user.setId(UUID.randomUUID());
        userList.add(user);
    }

    public boolean isUserDuplicated(User user) {
        synchronized (userList) {
            return userList.stream()
                    .anyMatch((existingUser) -> existingUser.getUserName().equals(user.getUserName()));
        }
    }

    public Optional<User> findByUserName(UserName userName) {
        synchronized (userList){
            return userList.stream()
                    .filter((user) -> user.getUserName().equals(userName))
                    .findAny();
        }
    }

    public Optional<User> findById(UUID id) {
        synchronized (userList){
            return userList.stream()
                    .filter((user) -> user.getId().equals(id))
                    .findAny();
        }
    }
}
