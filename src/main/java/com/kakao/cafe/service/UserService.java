package com.kakao.cafe.service;

import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.model.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDao userDAO;

    public UserService(UserDao userDAO) {
        this.userDAO = userDAO;
    }

    public UserDto filterUserById(String userId) {
        return voToDtoMapper(userDAO.filterUserById(userId));
    }

    public List<UserDto> getUserList() {
        return userDAO.findAllUser().stream()
                .map(this::voToDtoMapper)
                .collect(Collectors.toList());
    }

    public void signupUser(UserDto user) {
        // 비밀번호 암호화 로직 추가 가능
        userDAO.createUser(dtoToVoMapper(user));
    }

    private UserVo dtoToVoMapper(UserDto userDto) {
        return new UserVo(userDto.getUserId(), userDto.getPassword(), userDto.getName(), userDto.getEmail());
    }

    private UserDto voToDtoMapper(UserVo userVo) {
        return new UserDto(userVo.getUserId(), userVo.getPassword(), userVo.getName(), userVo.getEmail());
    }
}
