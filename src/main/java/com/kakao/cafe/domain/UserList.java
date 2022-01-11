package com.kakao.cafe.domain;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private static List<User> userList;

    private UserList() {
        userList = new ArrayList<>();
    }

    private static class UserListHelper {
        private static final UserList instance = new UserList();
    }

    public static UserList getInstance() {
        return UserListHelper.instance;
    }

    public void addUser(User user){
        userList.add(user);
    }

    public static User findByUserId(String userId){
        for(User user: userList){
            if(user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
}
