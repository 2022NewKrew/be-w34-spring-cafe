package com.kakao.cafe.model;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private static List<User> userList = new ArrayList<>();

    public UserList (){
    }

    public static void addUserList(User user){
        userList.add(user);
    }
}
