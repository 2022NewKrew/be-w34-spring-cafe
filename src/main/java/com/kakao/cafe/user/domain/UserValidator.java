package com.kakao.cafe.user.domain;

import com.kakao.cafe.user.exception.DuplicatedEmailException;
import com.kakao.cafe.user.exception.DuplicatedNicknameException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public void validate(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicatedEmailException();
        }

        if (userRepository.existsByNickname(user.getNickname())) {
            throw new DuplicatedNicknameException();
        }
    }
}
