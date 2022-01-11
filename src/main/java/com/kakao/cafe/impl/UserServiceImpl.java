package com.kakao.cafe.impl;

import com.kakao.cafe.service.UserService;
import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("userService")
public class UserServiceImpl implements UserService {
    private final List<User> userList = new ArrayList<>();

    @Override
    public void insertUser(User user) {
        userList.add(user);
    }

    @Override
    public List<User> getUserList() {
        return userList;
    }

    @Override
    public User getUserByUserId(String userId) {
        for (User user : userList) {
            if (Objects.equals(user.getUserId(), userId)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void updateUser(User updateUser) {
        String userId = updateUser.getUserId();

        for (User user : userList) {
            if (Objects.equals(user.getUserId(), userId)) {
                user.updateProfile(updateUser);
                return;
            }
        }

    }
}
