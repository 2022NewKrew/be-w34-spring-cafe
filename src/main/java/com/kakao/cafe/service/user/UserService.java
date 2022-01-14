package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.UserInfo;
import com.kakao.cafe.domain.user.UserList;

public class UserService {
    private UserService() {

    }

    public static void userSingUp(UserInfo userInfo) {
        UserList userList = UserList.getInstance();
        userList.addUser(userInfo);
    }
}
