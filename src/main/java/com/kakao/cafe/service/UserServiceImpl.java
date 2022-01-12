package com.kakao.cafe.service;

import com.kakao.cafe.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("userService")
public class UserServiceImpl implements UserService{
    private final List<User> users = new ArrayList<>();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void join(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        users.add(user);
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User findById(String userId) {
        return users.stream()
                .filter(user -> Objects.equals(user.getUserId(), userId))
                .findFirst()
                .orElse(null);
    }
}
