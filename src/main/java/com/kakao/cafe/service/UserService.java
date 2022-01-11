package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(UserDto userDto) {
        userRepository.save(userDto);
    }

    public List<UserDto> readAll() {
        return userRepository.findAll();
    }

    public Optional<UserDto> read(String userId) {
        return userRepository.findByUserId(userId);
    }
}
