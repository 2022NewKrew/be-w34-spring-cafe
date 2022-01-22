package com.kakao.cafe.service;

import com.kakao.cafe.domain.auth.Auth;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.auth.AuthLoginDto;
import com.kakao.cafe.exception.NotFoundException;
import com.kakao.cafe.exception.UnauthorizedException;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    @Autowired
    public AuthService(@Qualifier("userRepositoryJdbcImpl") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Auth login(AuthLoginDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UnauthorizedException("해당 이메일 사용자가 없습니다."));
        if (!user.authenticate(dto.getPassword())) {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }
        return new Auth(user.getId(), user.getName());
    }
}
