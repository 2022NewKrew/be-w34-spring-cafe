package com.kakao.cafe.service;

import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserDao userDAO;

    public UserService(UserDao userDAO) {
        this.userDAO = userDAO;
    }

    public User filterUserById(String userId) {
        return userDAO.filterUserById(userId);
    }

    public List<User> getUserList() {
        return userDAO.findAllUser();
    }

    public void signupUser(User user) {
        // 비밀번호 암호화 로직 추가 가능
        userDAO.createUser(user);
    }

}
