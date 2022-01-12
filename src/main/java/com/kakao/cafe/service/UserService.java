package com.kakao.cafe.service;

import com.kakao.cafe.dao.user.UserDao;
import com.kakao.cafe.dto.UserElementDto;
import com.kakao.cafe.dto.UserInformationDto;
import com.kakao.cafe.dto.UserRegisterDto;
import com.kakao.cafe.dto.UserUpdateDto;
import com.kakao.cafe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void updateUser(String userId, UserUpdateDto userUpdateDto) {
        User user = userDao.get(userId);
        if (user.getPassword().equals(userUpdateDto.getPassword())) {
            userDao.update(userId, userUpdateDto.getName(), userUpdateDto.getEmail());
            return;
        }
        throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }

    public List<UserElementDto> getUserElementDtos() {
        List<User> users = userDao.getUsers();
        return IntStream
                .range(0, users.size())
                .mapToObj(index -> new UserElementDto(users.get(index), index + 1))
                .collect(Collectors.toList());
    }

    public UserInformationDto findUserByUserId(String userId) {
        User user = userDao.get(userId);
        return new UserInformationDto(user.getUserId(), user.getName(), user.getEmail());
    }

    public void createUser(UserRegisterDto userRegisterDto) {
        userDao.add(
                new User(
                        userRegisterDto.getUserId(),
                        userRegisterDto.getPassword(),
                        userRegisterDto.getName(),
                        userRegisterDto.getEmail()
                )
        );
    }
}
