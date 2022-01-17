package com.kakao.cafe.service;

import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.dto.user.*;
import com.kakao.cafe.exception.InputDataException;
import com.kakao.cafe.exception.LoginFailedException;
import com.kakao.cafe.vo.UserVo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDao userDao;
    private final ModelMapper modelMapper;

    public UserService(UserDao userDao, ModelMapper modelMapper) {
        this.userDao = userDao;
        this.modelMapper = modelMapper;
    }

    public void addUser(UserDto userDto) throws InputDataException {
        if (existUserId(userDto.getUserId())) {
            throw new InputDataException("이미 존재하는 아이디입니다.");
        }
        UserVo userVo = modelMapper.map(userDto,UserVo.class);
        userDao.save(userVo);
    }

    public UserInfoDto getUser(String userId) {
        UserVo userVo = userDao.findByUserId(userId);
        return modelMapper.map(userVo, UserInfoDto.class);
    }

    public UserProfileDto getUserProfile(String userId) {
        UserVo userVo = userDao.findByUserId(userId);
        return modelMapper.map(userVo,UserProfileDto.class);
    }

    public List<UserInfoDto> getUsers() {
        return userDao.findAll().stream()
                .map(userVo -> modelMapper.map(userVo,UserInfoDto.class))
                .collect(Collectors.toList());
    }

    public void updateUser(UserDto userDto, String curPassword) throws InputDataException {
        if (!validateUser(userDto.getUserId(),curPassword)) {
            throw new InputDataException("비밀번호가 틀립니다.");
        }
        UserVo userVo = modelMapper.map(userDto,UserVo.class);
        userDao.update(userVo);
    }

    public boolean existUserId(String userId) {
        UserVo matchedUser = userDao.findByUserId(userId);
        if (matchedUser == null) {
            return false;
        }
        return true;
    }

    public boolean validateUser(String userId, String password) {
        UserVo matchedUser = userDao.findByUserId(userId);
        if (matchedUser == null) {
            return false;
        }
        return matchedUser.getPassword().equals(password);
    }

    public UserSessionDto login(UserLoginDto userLoginDto) throws LoginFailedException {
        if (!validateUser(userLoginDto.getUserId(),userLoginDto.getPassword())) {
            throw new LoginFailedException("로그인에 실패하였습니다.");
        }
        UserSessionDto sessiondUser = new UserSessionDto();
        sessiondUser.setUserId(userLoginDto.getUserId());
        return sessiondUser;
    }

}
