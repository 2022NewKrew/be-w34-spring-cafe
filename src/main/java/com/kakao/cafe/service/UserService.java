package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.dto.UserLoginResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService() {
        userDao = UserDao.getInstance();
    }

    public boolean createUser(User user) {
        final boolean isDuplicateUser = userDao.checkDuplicateUser(user);

        if (isDuplicateUser) {
            return false;
        }

        userDao.createUser(user);
        return true;
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }
    
    public User getUser(long userId) {
        return userDao.getUser(userId);
    }

    public User loginUser(UserLoginResponse userLoginResponse) {
        return userDao.loginUser(userLoginResponse.getEmail(), userLoginResponse.getPassword());
    }
}
