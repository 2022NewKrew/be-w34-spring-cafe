package com.kakao.cafe.entity;

import com.kakao.cafe.controller.UserIndexController;

import java.util.List;
import java.util.ArrayList;


public class UserList {
    private List<User> userList;
    private UserIndexController userIndexController;

    public UserList() {
        userList = new ArrayList<>();
        userIndexController = new UserIndexController();
    }

    public void addUser(User newUser) {
        userList.add(newUser);
        userIndexController.indexing(userList);
    }

    public User findUser(String userId) {
        for(int i=0; i<userList.size(); i++) {
            if(userList.get(i).getUserId().equals(userId)) {
                return userList.get(i);
            }
        }
        return null;
    }


    public List<User> getUserList() {
        return userList;
    }
}
