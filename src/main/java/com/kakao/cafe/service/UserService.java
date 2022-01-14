package com.kakao.cafe.service;

import com.kakao.cafe.dto.user.SignUpDTO;
import com.kakao.cafe.dto.user.UserDTO;
import com.kakao.cafe.repository.UserMemoryRepository;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository = new UserMemoryRepository();

    public Long signUp(SignUpDTO signUpDTO) {
        if(validateDuplicateUserId(signUpDTO)){
            return Long.MIN_VALUE;
        }
        return userRepository.save(signUpDTO).getId();
    }

    private boolean validateDuplicateUserId(SignUpDTO signUpDTO){
        //중복 검증
        return userRepository.findAll().stream()
                .filter(user -> user.getUserId().equals(signUpDTO.getUserId()))
                .count() > 0;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDTO getUser(String userId) {
        return userRepository.findByUserId(userId).get();
    }
    public UserDTO getUser(Long id) {
        return userRepository.findById(id).get();
    }
}