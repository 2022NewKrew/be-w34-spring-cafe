package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User join(User user){
        return userRepository.save(user);
    }

    public Optional<User> findProfile(String userId){
        return userRepository.findById(userId);
    }

    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public boolean checkDuplicateUserId(String userId){
        Optional<User> findUser = userRepository.findById(userId);

        return findUser.isPresent();
    }
}
