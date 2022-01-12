package com.kakao.cafe.service;

import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("userService2")
public class UserServiceImpl2 implements UserService {
    private final List<User> users = new ArrayList<>();

    @Override
    public void join(User user) {
        users.add(user);
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User findById(String userId) {
        for (User user : users) {
            if (Objects.equals(user.getUserId(), userId)) {
                return user;
            }
        }
        return null;
    }
}
