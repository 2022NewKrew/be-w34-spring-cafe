package com.kakao.cafe.web.service;

import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.dto.UserDTO;
import com.kakao.cafe.web.repository.MemoryUserRepository;
import com.kakao.cafe.web.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository memoryUserRepository;

    public UserService() {
        this.memoryUserRepository = new MemoryUserRepository();
    }

    public User signUp(UserDTO userDTO) {
       return memoryUserRepository.save(userDTO);
    }

    public List<User> getUserList() {
        return memoryUserRepository.getUserList();
    }

    public User getUserById(String userId) {
        return memoryUserRepository.getUserById(userId);
    }
}
