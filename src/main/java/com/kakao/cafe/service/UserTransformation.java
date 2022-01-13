package com.kakao.cafe.service;

import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.dto.UserListDto;
import com.kakao.cafe.dto.UserProfileDto;

public class UserTransformation {
    public UserListDto toUserListDto(UserDao userDao) {
        return new UserListDto(userDao.getIndex(), userDao.getId(), userDao.getName(), userDao.getEmail());
    }

    public UserProfileDto toUserProfileDto(UserDao userDao) {
        return new UserProfileDto(userDao.getId(), userDao.getName(), userDao.getEmail());
    }
}
