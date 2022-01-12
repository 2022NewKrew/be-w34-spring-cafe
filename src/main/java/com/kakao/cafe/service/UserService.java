package com.kakao.cafe.service;

import com.kakao.cafe.dao.user.UserDao;
import com.kakao.cafe.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void updateUser(String userId, String password, String name, String email) {
        User user = userDao.findUserById(userId);
        if (user.isPassword(password)) {
            userDao.update(userId, name, email);
            return;
        }
        throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public User findUserByUserId(String userId) {
        return userDao.findUserById(userId);
    }

    public void createUser(String userId, String password, String name, String email) {
        userDao.addUser(userId, password, name, email);
    }
}
