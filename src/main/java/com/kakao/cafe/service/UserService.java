package com.kakao.cafe.service;

import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.model.vo.UserVo;
import com.kakao.cafe.repository.UserDao;
import com.kakao.cafe.service.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserDao userDao;
    private final UserValidation userValidation;
    private final ModelMapper modelMapper;

    public UserDto filterUserById(String userId) {
        UserVo userVo = userDao.filterUserById(userId);
        userValidation.validateUser(userVo);
        return modelMapper.map(userVo, UserDto.class);
    }

    public List<UserDto> getUserList() {
        return userDao.findAllUser().stream()
                .map(userVo -> modelMapper.map(userVo, UserDto.class))
                .collect(Collectors.toList());
    }

    public void signupUser(UserDto user) {
        UserVo userVo = userDao.filterUserById(user.getUserId());
        userValidation.validateSignup(userVo);
        userDao.createUser(modelMapper.map(user, UserVo.class));
    }

    public void updateUser(UserDto user, String newPassword) {
        UserVo userVo = userDao.filterUserById(user.getUserId());
        userValidation.validateUpdate(userVo.getPassword(), user.getPassword());
        user.setPassword(newPassword);
        userDao.updateUser(modelMapper.map(user, UserVo.class));
    }

    public UserDto loginUser(String userId, String password) {
        UserVo userVo = userDao.filterUserById(userId);
        userValidation.validateLogin(userVo, password);
        return modelMapper.map(userVo, UserDto.class);
    }

}
