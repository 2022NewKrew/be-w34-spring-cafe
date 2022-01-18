package com.kakao.cafe.service;

import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.dto.user.*;
import com.kakao.cafe.exception.InputDataException;
import com.kakao.cafe.exception.LoginFailedException;
import com.kakao.cafe.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final ModelMapper modelMapper;

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
        String name = validateUser(userDto.getUserId(),curPassword);
        if (name == null) {
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

    public String validateUser(String userId, String password) {
        UserVo matchedUser = userDao.findByUserId(userId);
        if (matchedUser == null) {
            return null;
        }
        if (!matchedUser.getPassword().equals(password)) {
            return null;
        }
        return matchedUser.getName();
    }

    public UserSessionDto login(UserLoginDto userLoginDto) throws LoginFailedException {
        String name = validateUser(userLoginDto.getUserId(),userLoginDto.getPassword());
        if (name == null) {
            throw new LoginFailedException("로그인에 실패하였습니다.");
        }
        UserSessionDto sessiondUser = new UserSessionDto();
        sessiondUser.setUserId(userLoginDto.getUserId());
        sessiondUser.setName(name);
        return sessiondUser;
    }

}
