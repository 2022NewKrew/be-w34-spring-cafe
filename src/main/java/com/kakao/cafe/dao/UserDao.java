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

    public int getIndex(String userId) {
        List<Integer> resultIndex = users.stream().filter(user -> user.getUserId().equals(userId)).map(user -> users.indexOf(user)).collect(Collectors.toList());
        if(resultIndex.size() == 0)
            return -1;
        return resultIndex.get(0);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void updateUser(User user) {
        int index = getIndex(user.getUserId());
        users.set(index, user);
    }

}
