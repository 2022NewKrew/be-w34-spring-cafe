package com.kakao.cafe.service;

import com.kakao.cafe.domain.dto.UserSignUpDTO;
import com.kakao.cafe.domain.dto.UserViewDTO;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void signUp(UserSignUpDTO userSignUpDTO) {
        userRepository.signUp(userSignUpDTO);
    }

    public List<UserViewDTO> findAllUsers() {
        return userRepository.findAllUsers();
    }

    public UserViewDTO findUserById(String userId) {
        return userRepository.findUserByUserId(userId);
    }
}
