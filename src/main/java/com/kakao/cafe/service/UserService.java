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

    public UserDto findByUserId(String userId) {
        return UserDto.from(userRepository.findByUserId(userId));
    }

    public int updateUser(String userId, UserDto userDto) {
        User user= userRepository.findByUserId(userId);
        user.update(userDto.getPassword(), userDto.getName(), userDto.getEmail());
        return userRepository.save(user);
    }

    public int create(UserDto userDto) {
        User user = userDto.toEntity();
        return userRepository.save(user);
    }
}
