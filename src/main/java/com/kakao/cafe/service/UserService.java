package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signup(UserDto user) {
        userRepository.save(user);
    }

    public UserDto findById(String userId) {
        UserDto user = userRepository.findById(userId);
        if (user == null)
            throw new NoSuchElementException("해당 id를 가진 사용자가 없음");
        return user;
    }

    public UserDto findByName(String name) {
        UserDto user = userRepository.findByName(name);
        if (user == null)
            throw new NoSuchElementException("해당 name을 가진 사용자가 없음");
        return user;
    }

    public List<UserDto> getUserList() {
        return userRepository.getAllUsers();
    }
}
