package com.kakao.cafe.web.service;

import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.dto.UserCreateDTO;
import com.kakao.cafe.web.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository memoryUserRepository;

    public UserService(UserRepository memoryUserRepository) {
        this.memoryUserRepository = memoryUserRepository;
    }

    public User signUp(UserCreateDTO userCreateDTO) {
       return memoryUserRepository.save(userCreateDTO);
    }

    public List<User> getUserList() {
        return memoryUserRepository.getUserList();
    }

    public User getUserByUserId(String userId) {
        return memoryUserRepository.getUserByUserId(userId);
    }

    public User updateUser(UserCreateDTO userUpdateDTO) {
        return memoryUserRepository.update(userUpdateDTO);
    }
}
