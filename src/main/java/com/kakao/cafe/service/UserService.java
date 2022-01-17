package com.kakao.cafe.service;

import com.kakao.cafe.controller.dto.request.UserLoginRequestDto;
import com.kakao.cafe.controller.dto.request.UserSignUpRequestDto;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.exception.UserNotFoundException;
import com.kakao.cafe.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signUp(UserSignUpRequestDto userSignUpRequestDto) {
        User user = User.builder()
                .userId(userSignUpRequestDto.getUserId())
                .password(userSignUpRequestDto.getPassword())
                .name(userSignUpRequestDto.getName())
                .email(userSignUpRequestDto.getEmail())
                .build();

        userRepository.save(user);
    }

    public User findUserByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    throw new UserNotFoundException("사용자를 찾을수 없습니다.");
                });

    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User login(UserLoginRequestDto userLoginRequestDto) {
        User loginUser = findUserByUserId(userLoginRequestDto.getUserId());
        if (loginUser.getPassword().equals(userLoginRequestDto.getPassword())) {
            return loginUser;
        }

        throw new IllegalArgumentException("아이디 또는 비밀번호가 맞지 않습니다.");
    }
}
