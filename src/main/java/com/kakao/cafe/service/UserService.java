package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.UserRepository;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public void userCreate(User user){
        userRepository.userAdd(user);
    }
}
