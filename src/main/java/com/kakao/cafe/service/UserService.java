package com.kakao.cafe.service;

import com.kakao.cafe.domain.UserInfo;
import com.kakao.cafe.domain.UserList;

public class UserService {
    public static void userSingUp(UserInfo userInfo){
        UserList userList = UserList.getInstance();
        userList.addUser(userInfo);
    }
}
