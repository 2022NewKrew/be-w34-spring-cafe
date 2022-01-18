package com.kakao.cafe.service;

import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.exception.*;
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
        checkDuplicateUser(duplicateUser);
        userDao.addUser(user);
    }

    public void updateUser(User user, User loginUser) throws Exception {
        checkLogin(loginUser);
        checkSameUser(user, loginUser);
        checkPassword(user.getPassword(), loginUser.getPassword());
        userDao.updateUser(user);
    }

    public User login(String userId, String password) throws Exception {
        User user = getUser(userId);
        checkExistUser(user);
        checkPassword(password, user.getPassword());
        return user;
    }

    private void checkDuplicateUser(User duplicateUser) throws DuplicateUserException {
        if(duplicateUser != null)
            throw new DuplicateUserException();
    }

    public void checkExistUser(User user) throws UserNotExistException {
        if(user == null)
            throw new UserNotExistException();
    }

    public void checkLogin(User loginUser) throws NotLoginException {
        if(loginUser == null)
            throw new NotLoginException();
    }

    public void checkSameUser(User user, User loginUser) throws IncorrectUserException {
        if(!user.getUserId().equals(loginUser.getUserId()))
            throw new IncorrectUserException();
    }

    public void checkPassword(String password, String loginPassword) throws IncorrectPasswordException {
        if(!password.equals(loginPassword))
            throw new IncorrectPasswordException();
    }

}
