package com.kakao.cafe.service;

import com.kakao.cafe.controller.dto.UserSignUpRequestDto;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.exception.UserNotFoundException;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void singUp(UserSignUpRequestDto userSignUpRequestDto) {
        User user = new User(userSignUpRequestDto.getUserId(), userSignUpRequestDto.getPassword()
                , userSignUpRequestDto.getName(), userSignUpRequestDto.getEmail());
        userRepository.save(user);
    }

    public User findUserByUserId(String userId) {
        return userRepository.findUserByUserId(userId)
                .orElseThrow(() -> {
                    throw new UserNotFoundException("사용자를 찾을수 없습니다.");
                });

    }

}
