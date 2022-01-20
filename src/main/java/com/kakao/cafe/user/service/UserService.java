package com.kakao.cafe.user.service;

import com.kakao.cafe.common.exception.AuthException;
import com.kakao.cafe.common.exception.ErrorCode;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.request.SignUpRequest;
import com.kakao.cafe.user.dto.request.loginRequest;
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

    public UserResponse login(loginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new AuthException(ErrorCode.USER_NOT_FOUND));
        if(!user.getPassword().equals(loginRequest.getPassword())){
            throw new AuthException(ErrorCode.INVALID_PASSWORD);
        }
        return UserResponse.of(user);
    }

    public UsersResponse findAll() {
        return UsersResponse.of(userRepository.findAll());
    }

    public UserResponse save(SignUpRequest signUpRequest) {
        User user = signUpRequest.toUser();
        validateDuplicateEmail(user);
        return UserResponse.of(userRepository.save(user));
    }

    private void validateDuplicateEmail(User user) {
        userRepository.findByEmail(user.getEmail())
            .ifPresent(s -> {
                throw new AuthException(ErrorCode.DUPLICATED_EMAIL);
            });
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new AuthException(ErrorCode.USER_NOT_FOUND));
        return UserResponse.of(user);
    }
}
