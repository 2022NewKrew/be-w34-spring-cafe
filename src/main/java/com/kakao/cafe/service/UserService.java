package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private UserRepository userRepository;

    @Autowired
    public UsersService(
            @Qualifier("jdbcUserRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long signUp(User user) {
        Long savedUserId = userRepository.save(user);
        return savedUserId;
    }

    public List<User> getUsersAll() {
        return userRepository.findAll();
    }

    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

}
