package com.kakao.cafe.user.service;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.SignUpDTO;
import com.kakao.cafe.user.dto.UpdateDTO;
import com.kakao.cafe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User signUp(SignUpDTO signUpDTO) {
        return userRepository.save(signUpDTO);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("유저가 존재하지 않습니다."));
    }

    public User updateUser(UpdateDTO updateDTO) {
        return userRepository.update(updateDTO);
    }
}
