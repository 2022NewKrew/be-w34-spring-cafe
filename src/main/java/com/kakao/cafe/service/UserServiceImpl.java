package com.kakao.cafe.service;

import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("userService")
public class UserServiceImpl implements UserService{
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
//        return users.stream()
//                .filter(user -> Objects.equals(user.getUserId(), userId))
//                .findAny()
//                .map()
//                .orElse(null);


        for (User user : users) {
            if (Objects.equals(user.getUserId(), userId)) {
                return user;
            }
        }

        return null;
    }
}
