package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.user.UserInfo;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    protected List<UserInfo> getUserList() {
        return List.copyOf(userList);
    }

    protected void addUser(UserInfo userInfo) {
        userList.add(userInfo);
    }

    public int getSize() {
        return userList.size();
    }

    protected Optional<UserInfo> findById(String userId) {
        return userList.stream().filter(userInfo -> userInfo.hasEqualId(userId)).findFirst();
    }
}
