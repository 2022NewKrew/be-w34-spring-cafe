package com.kakao.cafe.domain;


import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return userList;
    }

    public void addUser(UserInfo userInfo) {
        userList.add(userInfo);
    }

    public int getSize(){
        return userList.size();
    }

    public UserInfo findByName(String name) {
        UserInfo target = userList.stream().filter(userInfo -> userInfo.hasEqualName(name)).findFirst().orElse(null);
        Assert.notNull(target,"FIND Error: Null Object");
        return target;
    }
}
