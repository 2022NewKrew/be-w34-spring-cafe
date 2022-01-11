package com.kakao.cafe.user.service;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.request.SignUpRequest;
import com.kakao.cafe.user.dto.response.UserResponse;
import com.kakao.cafe.user.dto.response.UsersResponse;
import com.kakao.cafe.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UsersResponse findAll() {
        return UsersResponse.of(userRepository.findAll());
    }

    public UserResponse save(SignUpRequest signUpRequest) {
        User user = signUpRequest.toUser();
        return UserResponse.of(userRepository.save(user));
    }
}
