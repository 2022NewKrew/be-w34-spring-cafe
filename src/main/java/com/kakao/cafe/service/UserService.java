package com.kakao.cafe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.dto.UsersDto;
import com.kakao.cafe.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(UserDto userDTO) {
        User user = new User(userDTO.getUserId(), userDTO.getPassWord(), userDTO.getName(), userDTO.getEmail());

        userRepository.save(user);
    }

    public UsersDto findAll() {
        List<User> users = userRepository.findAll();

        return new UsersDto(users);
    }

    public UserDto findById(String userId) {
        User user = userRepository.findById(userId);

        return new UserDto(user);
    }

    public boolean login(String userId, String password) {
        return findById(userId).getPassWord().equals(password);
    }
}

