package com.kakao.cafe.service;

import com.kakao.cafe.Repository.UserRepository;
import com.kakao.cafe.domain.User;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(User user) {
        userRepository.create(user);
    }

    public List<User> getUserList() {
        return userRepository.getUserList();
    }

    public User findById(String userId) {
        return userRepository.findById(userId);
    }
}
