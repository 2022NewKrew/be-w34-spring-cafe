package com.kakao.cafe.service;

import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userRepository")
    UserRepository userRepository;

    @Override
    public void join(User user) {
        userRepository.createUser(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.readUsers();
    }

    @Override
    public User findById(String userId) {
        return userRepository.readUser(userId);
    }
}
