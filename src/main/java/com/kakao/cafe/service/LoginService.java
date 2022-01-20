package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.controller.dto.LoginRequest;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public User login(LoginRequest loginRequest){
        return userRepository
                .findByNicknameAndPassword(loginRequest.getNickname(), loginRequest.getPassword())
                .orElseThrow(IllegalArgumentException::new);
    }
}
