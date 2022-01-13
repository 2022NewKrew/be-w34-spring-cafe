package com.kakao.cafe.domain.user;

import org.springframework.util.Assert;

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

    public List<UserInfo> getUserList() {
        return List.copyOf(userList);
    }

    public void addUser(UserInfo userInfo) {
        userList.add(userInfo);
    }

    public int getSize() {
        return userList.size();
    }

    public UserInfo findById(String userId) {
        UserInfo target = userList.stream().filter(userInfo -> userInfo.hasEqualId(userId)).findFirst().orElse(null);
        Assert.notNull(target, "FIND Error: Null Object");
        return target;
    }
}
