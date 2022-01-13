package com.kakao.cafe.repository;

import com.kakao.cafe.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class UserRepository {
    private final List<User> userList = new ArrayList<>();
    private static Integer seq = 0;

    public void save(User user){
        user.setId(seq++);
        userList.add(user);
    }

    public List<User> getUserList() {
        return userList;
    }

}
