package com.kakao.cafe.web.user.domain;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private List<User> userList = new ArrayList<>();

    public List<User> getUserList() { return this.userList; }

    public void addUser(User user) {
        this.userList.add(user);
    }

    public User findUserById(String id) {
        for ( User user : this.userList ) {
            if (id.equals(user.getUserId()))
                return user;
        }
        return null;
    }
}
