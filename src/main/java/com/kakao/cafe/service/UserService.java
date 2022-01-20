package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.dto.UserLoginRequest;
import com.kakao.cafe.exception.SaveFailException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save(User user) {
        final boolean isExistUser = userDao.existsByEmailOrNickname(user.getEmail(), user.getNickname());

        if (isExistUser) {
            throw new SaveFailException();
        }

        userDao.save(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }
    
    public User findByUserId(long userId) {
        return userDao.findByUserId(userId);
    }

    public User findByEmailAndPassword(UserLoginRequest userLoginRequest) {
        return userDao.findByEmailAndPassword(userLoginRequest.getEmail(), userLoginRequest.getPassword());
    }
}
