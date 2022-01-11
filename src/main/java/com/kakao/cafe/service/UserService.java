package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signup(UserDto user) {
        userRepository.save(user);
    }

    public UserDto findUserById(String userId) {
        return userRepository.findById(userId);
    }

    public List<UserDto> getUserList() {
        return userRepository.getAllUsers();
    }
}
