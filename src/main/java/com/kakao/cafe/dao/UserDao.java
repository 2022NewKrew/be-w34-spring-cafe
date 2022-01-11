package com.kakao.cafe.dao;

import com.kakao.cafe.dto.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDao {

    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public User getUser(String userId) {
        List<User> resultUsers = users.stream().filter(user -> user.getUserId().equals(userId)).collect(Collectors.toList());
        if(resultUsers.size() == 0)
            return null;
        return resultUsers.get(0);
    }

    public void addUser(User user) {
        users.add(user);
    }

}
