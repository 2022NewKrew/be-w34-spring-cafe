package com.kakao.cafe.Service;

import com.kakao.cafe.Repository.UserDao;
import com.kakao.cafe.model.User.UserCreateRequestDto;
import com.kakao.cafe.model.User.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public List<UserResponseDto> getUserList() {
        return userDao.findAll();
    }

    public void signUp(UserCreateRequestDto user) {
        userDao.insert(user);
    }

    public UserResponseDto findUserById(Long id) {
        return userDao.findById(id);
    }
}
