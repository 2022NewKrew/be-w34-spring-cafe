package com.kakao.cafe.service;

import com.kakao.cafe.controller.UserDto;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }

    public UserDto findByUserId(String userId) {
        return UserDto.from(userRepository.findByUserId(userId).get());
    }

    public int update(String userId, UserDto userDto) {
        User user = userRepository.findByUserId(userId).get();
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return userRepository.save(user);
    }

    public int create(UserDto userDto) {
        User user = userDto.toEntity();
        return userRepository.save(user);
    }
}
