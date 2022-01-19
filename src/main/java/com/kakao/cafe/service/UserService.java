package com.kakao.cafe.service;

import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.exception.*;
import com.kakao.cafe.util.ErrorUtil;
import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public User getUser(String userId) {
        return userDao.getUser(userId);
    }

    public void addUser(User user) throws DuplicateUserException {
        User duplicateUser = getUser(user.getUserId());
        if(duplicateUser != null)
            throw new DuplicateUserException();
        userDao.addUser(user);
    }

    public void updateUser(User user, User loginUser) throws Exception {
        if(!ErrorUtil.checkSameString(user.getUserId(), loginUser.getUserId()))
            throw new IncorrectUserException();
        if(!ErrorUtil.checkSameString(user.getPassword(), loginUser.getPassword()))
            throw new IncorrectPasswordException();
        userDao.updateUser(user);
    }

    public User login(String userId, String password) throws Exception {
        User user = getUser(userId);
        if(user == null)
            throw new UserNotExistException();
        if(!ErrorUtil.checkSameString(password, user.getPassword()))
            throw new IncorrectPasswordException();
        return user;
    }

}
