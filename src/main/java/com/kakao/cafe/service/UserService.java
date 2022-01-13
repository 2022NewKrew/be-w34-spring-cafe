package com.kakao.cafe.service;

import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.exception.InputDataException;
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
        validateUserId(userDto);
        UserVo userVo = modelMapper.map(userDto,UserVo.class);
        userDao.save(userVo);
    }

    public UserDto gerUser(String userId) {
        UserVo userVo = userDao.findByUserId(userId);
        return modelMapper.map(userVo,UserDto.class);
    }

    public List<UserDto> getUsers() {
        return userDao.findAll().stream()
                .map(userVo -> modelMapper.map(userVo,UserDto.class))
                .collect(Collectors.toList());
    }

    public void updateUser(UserDto userDto, String curPassword) throws InputDataException {
        validatePassword(userDto,curPassword);
        UserVo userVo = modelMapper.map(userDto,UserVo.class);
        userDao.update(userVo);
    }

    public void validateUserId(UserDto userDto) throws InputDataException {
        UserVo matchedUser = userDao.findByUserId(userDto.getUserId());
        if (matchedUser != null) {
            throw new InputDataException("이미 존재하는 아이디입니다.");
        }
    }

    public void validatePassword(UserDto userDto, String curPassword) throws InputDataException {
        UserVo matchedUser = userDao.findByUserId(userDto.getUserId());
        if (!matchedUser.getPassword().equals(curPassword)) {
            throw new InputDataException("비밀번호가 틀립니다.");
        }
    }

}
