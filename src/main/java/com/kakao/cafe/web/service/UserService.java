package com.kakao.cafe.web.service;

import com.kakao.cafe.domain.Users;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<Users> userList = new ArrayList<>();
    private Long id = 0L;

    public List<Users> getUserList() {
        return userList;
    }

    public void addUser(Users user) {
        user.setId(id);
        id++;
        userList.add(user);
    }

    public Users getByUserId(Long id) {
        for (Users user : userList) {
            if (user.getId() == id)
                return user;
        }
        throw new IllegalArgumentException("해당 id를 가진 유저가 존재하지 않습니다.");
    }

}
