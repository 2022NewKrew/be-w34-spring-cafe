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
        checkPassword(findUser(userId), password);

        userDao.update(userId, name, email);
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public User findUserByUserId(String userId) {
        return findUser(userId);
    }

    public void createUser(String userId, String password, String name, String email) {
        userDao.addUser(userId, password, name, email);
    }

    private User findUser(String userId) {
        return userDao
                .findUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 사용자가 없습니다!"));
    }

    private void checkPassword(User user, String inputPassword) {
        if (!user.isPassword(inputPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
