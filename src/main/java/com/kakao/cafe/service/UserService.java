package com.kakao.cafe.service;

import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.model.vo.UserVo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDto filterUserById(String userId) {
        return voToDtoMapper(userDao.filterUserById(userId));
    }

    public List<UserDto> getUserList() {
        return userDao.findAllUser().stream()
                .map(this::voToDtoMapper)
                .collect(Collectors.toList());
    }

    public void signupUser(UserDto user) {
        // 아이디 중복 검사
        try {
            userDao.filterUserById(user.getUserId());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (EmptyResultDataAccessException e) {
            userDao.createUser(dtoToVoMapper(user));
        }
    }

    public void updateUser(UserDto user, String newPassword) {
        String oldPassword = userDao.filterUserById(user.getUserId()).getPassword();
        if (!oldPassword.equals(user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        user.setPassword(newPassword);
        userDao.updateUser(dtoToVoMapper(user));
    }

    public void loginUser(UserDto user, String password, HttpSession session) {
        if (!user.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        session.setAttribute("sessionedUser", user);
    }

    private UserVo dtoToVoMapper(UserDto userDto) {
        return new UserVo(userDto.getUserId(), userDto.getPassword(), userDto.getName(), userDto.getEmail());
    }

    private UserDto voToDtoMapper(UserVo userVo) {
        return new UserDto(userVo.getUserId(), userVo.getPassword(), userVo.getName(), userVo.getEmail());
    }
}
