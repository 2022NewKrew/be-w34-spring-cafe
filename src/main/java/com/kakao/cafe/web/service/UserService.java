package com.kakao.cafe.web.service;

import com.kakao.cafe.domain.Users;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<Users> userList = new ArrayList<>();
    private int id = 0;

    public List<Users> getUserList() {
        return userList;
    }

    public void addUser(Users user) {
        user.setId(id);
        id++;
        userList.add(user);
    }

    public Users getByUserId(int id) {
        for (Users user : userList) {
            if (user.getId() == id)
                return user;
        }
        throw new IllegalArgumentException("해당 id를 가진 유저가 존재하지 않습니다.");
    }

    public void updateUser(int id, Users updateUser, String newPassword) {
        if (!getByUserId(id).getPassword().equals((updateUser.getPassword())))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        if (!newPassword.isBlank())
            updateUser.setPassword(newPassword);
        userList.set(id, updateUser);
    }

}
