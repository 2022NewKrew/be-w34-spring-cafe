package com.kakao.cafe.service;

import com.kakao.cafe.domain.auth.Auth;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.auth.AuthRequest;
import com.kakao.cafe.exception.InvalidPasswordException;
import com.kakao.cafe.exception.UserNotFoundException;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Auth login(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 크루입니다."));

        if (!user.isEqualPassword(request.getPassword())) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }

        return new Auth(user.getId());
    }

}
