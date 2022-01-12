package com.kakao.cafe.user.service;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.request.SignUpRequest;
import com.kakao.cafe.user.dto.response.UserResponse;
import com.kakao.cafe.user.dto.response.UsersResponse;
import com.kakao.cafe.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final String DUPLICATED_EMAIL = "이미 존재하는 이메일입니다.";
    private static final String USER_NOT_FOUNT_MESSAGE = "해당 회원정보를 찾을 수 없습니다.";

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UsersResponse findAll() {
        return UsersResponse.of(userRepository.findAll());
    }

    public UserResponse save(SignUpRequest signUpRequest) {
        User user = signUpRequest.toUser(userRepository.autoIncrement());
        validateDuplicateEmail(user);
        return UserResponse.of(userRepository.save(user));
    }

    private void validateDuplicateEmail(User user) {
        userRepository.findByEmail(user.getEmail())
            .ifPresent(s -> {
                throw new IllegalArgumentException(DUPLICATED_EMAIL);
            });
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(USER_NOT_FOUNT_MESSAGE));
        return UserResponse.of(user);
    }
}
