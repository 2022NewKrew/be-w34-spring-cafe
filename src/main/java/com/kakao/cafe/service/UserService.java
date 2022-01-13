package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserProfileDto;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.repository.UserDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void signup(UserDto user) throws SQLException {
        userDao.save(user.toEntity());
    }

    public UserProfileDto findById(String userId) throws NoSuchElementException {
        User user = userDao.findByUserId(userId);

        return new UserProfileDto(user.getUserId(), user.getEmail(), user.getName());
    }

    public UserProfileDto findByName(String name) throws NoSuchElementException {
        User user = userDao.findByName(name);

        return new UserProfileDto(user.getUserId(), user.getEmail(), user.getName());
    }

    public List<UserProfileDto> getUserList() {
        List<UserProfileDto> userDtoList = new ArrayList<>();

        for (User user : userDao.findAll()) {
            userDtoList.add(new UserProfileDto(user.getUserId(), user.getEmail(), user.getName()));
        }

        return userDtoList;
    }

    public void updateUserProfile(UserProfileDto newProfile, String password) throws NoSuchElementException, IllegalArgumentException {
        User user = userDao.findByUserId(newProfile.getUserId());

        if (!(password != null && password.equals(user.getPassword()))) {
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }

        User newUser = new User(
                user.getUserId(),
                newProfile.getEmail(),
                newProfile.getName(),
                user.getPassword()
                );

        userDao.update(newUser);
    }
}
