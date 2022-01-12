package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.Users;
import com.kakao.cafe.dto.UserDto;

import java.util.List;

public class UserService {
    private Users users = new Users();

    public void addUser(UserDto userDto) {
        users.addUser(userDto);
    }

    public List<User> findAll() {
        return users.getUsers();
    }

    public User findById(int id) {
        return users.findById(id);
    }

    public int getCountOfUser() {
        return users.size();
    }

    public void updateUser(int id, UserDto userDto){
        users.updateUser(id, userDto);
    }
}
