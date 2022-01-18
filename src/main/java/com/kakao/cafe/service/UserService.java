package com.kakao.cafe.service;

import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.model.vo.UserVo;
import com.kakao.cafe.service.validation.UserValidation;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDao userDao;
    private final UserValidation userValidation;

    public UserService(UserDao userDao, UserValidation userValidation) {
        this.userDao = userDao;
        this.userValidation = userValidation;
    }

    public UserDto filterUserById(String userId) {
        UserVo userVo = userDao.filterUserById(userId);
        userValidation.validateUser(userVo);
        return voToDtoMapper(userVo);
    }

    public List<UserDto> getUserList() {
        return userDao.findAllUser().stream()
                .map(this::voToDtoMapper)
                .collect(Collectors.toList());
    }

    public void signupUser(UserDto user) {
        UserVo userVo = userDao.filterUserById(user.getUserId());
        userValidation.validateSignup(userVo);
        userDao.createUser(dtoToVoMapper(user));
    }

    public void updateUser(UserDto user, String newPassword) {
        UserVo userVo = userDao.filterUserById(user.getUserId());
        userValidation.validateUpdate(userVo.getPassword(), user.getPassword());
        user.setPassword(newPassword);
        userDao.updateUser(dtoToVoMapper(user));
    }

    public UserDto loginUser(String userId, String password) {
        UserVo userVo = userDao.filterUserById(userId);
        userValidation.validateLogin(userVo, password);
        return voToDtoMapper(userVo);
    }

    private UserVo dtoToVoMapper(UserDto userDto) {
        return new UserVo(userDto.getUserId(), userDto.getPassword(), userDto.getName(), userDto.getEmail());
    }

    private UserDto voToDtoMapper(UserVo userVo) {
        return new UserDto(userVo.getUserId(), userVo.getPassword(), userVo.getName(), userVo.getEmail());
    }
}
