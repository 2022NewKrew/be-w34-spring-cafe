package com.kakao.cafe.dao;

import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDao {

    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public User getUser(String userId) {
        return users.stream().filter(user -> user.getUserId().equals(userId)).findFirst().orElse(null);
    }

    public int getIndex(String userId) {
        return users.stream().filter(user -> user.getUserId().equals(userId)).map(user -> users.indexOf(user)).findFirst().orElse(-1);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void updateUser(User user) {
        int index = getIndex(user.getUserId());
        users.set(index, user);
    }

}
