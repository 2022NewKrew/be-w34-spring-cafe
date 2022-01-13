package com.kakao.cafe.service;

import com.kakao.cafe.domain.dto.UserCreateCommand;
import com.kakao.cafe.domain.dto.UserModifyCommand;
import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String name) {
        return userRepository.search(name);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUser();
    }

    public void createUser(UserCreateCommand ucc) {
        userRepository.store(ucc);
    }

    public void modifyUser(String userId, UserModifyCommand umc) {
        userRepository.modify(userId, umc);
    }
}
