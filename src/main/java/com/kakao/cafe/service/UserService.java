package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.dto.UserSignUpDto;
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

    public void signup(UserSignUpDto user) throws SQLException {
        userRepository.save(user);
    }

    public UserDto findById(String userId) throws NoSuchElementException {
        User user = userRepository.findById(userId);
        if (user == null)
            throw new NoSuchElementException("해당 id를 가진 사용자가 없음");

        return new UserDto(user.getUserId(), user.getEmail(), user.getName());
    }

    public UserDto findByName(String name) throws NoSuchElementException {
        User user = userRepository.findByName(name);
        if (user == null)
            throw new NoSuchElementException("해당 name을 가진 사용자가 없음");

        return new UserDto(user.getUserId(), user.getEmail(), user.getName());
    }

    public List<UserDto> getUserList() {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            userDtoList.add(new UserDto(user.getUserId(), user.getEmail(), user.getName()));
        }

        return userDtoList;
    }
}
