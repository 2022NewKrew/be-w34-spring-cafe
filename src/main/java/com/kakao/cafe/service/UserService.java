package com.kakao.cafe.service;

import com.kakao.cafe.model.User;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(User user) {
        if(!userRepository.signUp(user)){
            throw new IllegalArgumentException();
        }
    }

}
