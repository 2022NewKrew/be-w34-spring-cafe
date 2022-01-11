package com.kakao.cafe.service;

import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{
    private final List<User> users = new ArrayList<>();

    @Override
    public void join(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }
}
