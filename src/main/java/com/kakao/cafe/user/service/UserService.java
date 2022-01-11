package com.kakao.cafe.user.service;

import com.kakao.cafe.user.entity.User;
import com.kakao.cafe.user.repository.MemoryUserRepository;
import com.kakao.cafe.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository = new MemoryUserRepository();

    public void addUser(User user) {
        userRepository.save(user);
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findOneByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

}
