package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dao.UserDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService() {
        this.userDao = UserDao.getInstance();
    }

    public void createUser(User user) {
        if (userDao.checkDuplicateUser(user)) {
            return;
        }

        userDao.createUser(user);
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }
    
    public User getUser(long userId) {
        return userDao.getUser(userId);
    }
}
