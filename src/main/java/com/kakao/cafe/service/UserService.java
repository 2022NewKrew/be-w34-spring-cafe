package com.kakao.cafe.service;

import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.UserRepository;

import java.util.List;

public class UserService {
    private static final UserRepository userRepository = new UserRepository();
    public List<UserDao> getUserList() {
        return userRepository.selectAll();
    }
}
