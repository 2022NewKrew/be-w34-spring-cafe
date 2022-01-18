package com.kakao.cafe.service;

import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.exception.*;
import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;
    private final ErrorService errorService;

    public UserService(UserDao userDao, ErrorService errorService) {
        this.userDao = userDao;
        this.errorService = errorService;
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public User getUser(String userId) {
        return userDao.getUser(userId);
    }

    public void addUser(User user) throws DuplicateUserException {
        User duplicateUser = getUser(user.getUserId());
        errorService.checkDuplicateUser(duplicateUser);
        userDao.addUser(user);
    }

    public void updateUser(User user, User loginUser) throws Exception {
        errorService.checkLogin(loginUser);
        errorService.checkSameUser(user, loginUser);
        errorService.checkPassword(user.getPassword(), loginUser.getPassword());
        userDao.updateUser(user);
    }

    public User login(String userId, String password) throws Exception {
        User user = getUser(userId);
        errorService.checkExistUser(user);
        errorService.checkPassword(password, user.getPassword());
        return user;
    }

}
