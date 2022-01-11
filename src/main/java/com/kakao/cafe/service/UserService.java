package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.repository.UserMemoryRepository;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository = new UserMemoryRepository();

    public Long signUp(UserDTO userDTO) {
        //validateDuplicateUserId(userDTO);
        User user = new User(userDTO);
        return userRepository.save(user).getId();
    }

    private void validateDuplicateUserId(UserDTO user){
        //중복 검증
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(String userId) {
        return userRepository.findByUserId(userId).get();
    }
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }
}