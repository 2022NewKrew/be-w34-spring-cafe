package com.kakao.cafe.service;

import com.kakao.cafe.model.User;

import java.util.List;

public class UserService {

    public User getUser(String userId, List<User> users) {
        for(int i = 0; i < users.size(); i++) {
            if(userId.equals(users.get(i).getUserId()))
                return users.get(i);
        }
        return null;
    }

}
