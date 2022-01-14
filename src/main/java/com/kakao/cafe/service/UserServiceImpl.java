package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserRegistrationDto;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.repository.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

        public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void join(UserRegistrationDto userDto) {
       userRepository.createUser(userDto);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.readUsers();
    }

    @Override
    public User findById(String userId) {
        return userRepository.readUser(userId);
    }
}
