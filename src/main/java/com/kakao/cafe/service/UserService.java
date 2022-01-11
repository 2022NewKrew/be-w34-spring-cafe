package com.kakao.cafe.service;

import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addUser(UserDto userDto) {
        UserVo userVo = dtoToVo(userDto);
        userDao.addUser(userVo);
    }

    public UserDto gerUser(String userId) {
        UserVo userVo = userDao.getUser(userId);
        return voToDto(userVo);
    }

    public List<UserDto> getUsers() {
        return userDao.getUsers().stream()
                .map(this::voToDto)
                .collect(Collectors.toList());
    }

    public boolean updateUser(UserDto userDto, String curPassword) {
        UserVo matchedUser = userDao.getUser(userDto.getUserId());
        if (matchedUser.getPassword().equals(curPassword)) {
            UserVo userVo = dtoToVo(userDto);
            userDao.updateUser(userVo);
            return true;
        }
        return false;
    }

    private UserVo dtoToVo(UserDto userDto) {
        return new UserVo(userDto.getUserId(), userDto.getPassword(), userDto.getName(), userDto.getEmail());
    }

    private UserDto voToDto(UserVo userVo) {
        UserDto userDto = new UserDto();
        userDto.setUserId(userVo.getUserId());
        userDto.setName(userVo.getName());
        userDto.setEmail(userVo.getEmail());
        return userDto;
    }
}
