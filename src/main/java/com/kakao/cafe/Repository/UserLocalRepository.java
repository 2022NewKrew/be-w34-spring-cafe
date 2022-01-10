package com.kakao.cafe.Repository;

import com.kakao.cafe.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserLocalRepository implements UserRepository {
    List<User> userList;

    public UserLocalRepository() {
        userList = new ArrayList<>();
    }

    @Override
    public void create(User user) {
        userList.add(user);
    }

    @Override
    public List<User> getUserList() {
        return userList;
    }

    @Override
    public User findById(String userId) {
        return userList.stream()
                .filter(x -> x.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }
}
