package com.kakao.cafe.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserList {
    private static List<User> users = new ArrayList<>();

    public UserList (){
    }

    public static void addUserList(User user){
        users.add(user);
    }

    public static List<User> getUsers(){
        return users;
    }

    public static User getUserByUserId(String userId){
        for(User user : users){
            if(user.getUserId().equals(userId)) return user;
        }
        return null;
    }
}
