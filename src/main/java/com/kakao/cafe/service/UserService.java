package com.kakao.cafe.service;

import com.kakao.cafe.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> userList = new ArrayList<>();

    public List<User> getUserList() {
        return userList;
    }

    public void registerUser(User user) {
        userList.add(user);
    }

    public User findById(String userId) {
        for(User user : userList) {
            if (user.getUserId().equals(userId)) return user;
        }
        return null;
    }

}
