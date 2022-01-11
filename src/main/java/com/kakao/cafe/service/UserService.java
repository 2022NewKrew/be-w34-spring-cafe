package com.kakao.cafe.service;

import com.kakao.cafe.controller.UserDto;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    public String create(UserDto userDto) {
        User user = userDto.toEntity();
        userRepository.save(user);
        return user.getUserId();
    }
}
