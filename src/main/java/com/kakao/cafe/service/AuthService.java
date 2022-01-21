package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserLoginDto;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.repository.JdbcUserRepository;

import java.util.Objects;

public class AuthService {

    private final JdbcUserRepository jdbcUserRepository;

    public AuthService(JdbcUserRepository jdbcUserRepository) {
        this.jdbcUserRepository = jdbcUserRepository;
    }

    public User login(UserLoginDto userLoginDto) {
        User user = jdbcUserRepository.readByUserId(userLoginDto.getUserId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 닉네임입니다."));

        if (!user.isEqualPassword(userLoginDto.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }
}
