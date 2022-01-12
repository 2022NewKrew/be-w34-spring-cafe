package com.kakao.cafe.user.service;

import com.kakao.cafe.user.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserServiceImp implements UserService{
    static List<String> userIds = new ArrayList<>();
    static HashMap<String, User> userMap = new HashMap<>();

    @Override
    public User createUser(String id, String password, String name, String email, String age) {
        User user = new User(id, password, name, email, age);

        userIds.add(user.getUserId());
        userMap.put(user.getUserId(), user);

        return  user;
    }

    @Override
    public void addUser(User user) {
        userIds.add(user.getUserId());
        userMap.put(user.getUserId(),user);
    }

    @Override
    public List<User> readUsers() {
        List<User> result = new ArrayList<>();
        for(String userId : userIds){
            result.add(userMap.get(userId));
        }
        return result;
    }

    @Override
    public User readUser(String userId) {
        return userMap.get(userId);
    }

    @Override
    public void updateUser(String userId, User user) {
        userMap.put(userId,user);
    }

    @Override
    public void deleteUser(String userId) {
        userIds.remove(userId);
    }
}
