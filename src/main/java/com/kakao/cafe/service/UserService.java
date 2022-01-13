package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserProfileDto;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.repository.UserRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signup(UserDto user) throws SQLException {
        userRepository.save(user.toEntity());
    }

    public UserProfileDto findById(String userId) throws NoSuchElementException {
        User user = userRepository.findById(userId);

        return new UserProfileDto(user.getUserId(), user.getEmail(), user.getName());
    }

    public UserProfileDto findByName(String name) throws NoSuchElementException {
        User user = userRepository.findByName(name);

        return new UserProfileDto(user.getUserId(), user.getEmail(), user.getName());
    }

    public List<UserProfileDto> getUserList() {
        List<UserProfileDto> userDtoList = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            userDtoList.add(new UserProfileDto(user.getUserId(), user.getEmail(), user.getName()));
        }

        return userDtoList;
    }

    public void updateUserProfile(UserProfileDto newProfile) {
        userRepository.update(newProfile);
    }

    public boolean checkPassword(String userId, String password) throws NoSuchElementException {
        User user = userRepository.findById(userId);

        return (password != null && password.equals(user.getPassword()));
    }
}
