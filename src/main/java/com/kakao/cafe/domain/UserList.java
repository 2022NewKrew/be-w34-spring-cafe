package com.kakao.cafe.domain;


import java.util.ArrayList;
import java.util.List;

public class UserList {
    private final List<UserInfo> userList = new ArrayList<>();

    private UserList() {

    }

    private static class InnerInstanceClass {
        private static final UserList instance = new UserList();
    }

    public static UserList getInstance() {
        return InnerInstanceClass.instance;
    }

    public List<UserInfo> getUserList(){
        return userList;
    }
    public void addUser(UserInfo userInfo) {
        userList.add(userInfo);
    }
}
