package com.kakao.cafe.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class UserRepository {
    private static List<User> userList = new ArrayList<>();
    private static Long seq = 0L;

    public void save(User user){
        user.setId(seq++);
        userList.add(user);
    }

    public static List<User> getUserList() {
        return userList;
    }
}
