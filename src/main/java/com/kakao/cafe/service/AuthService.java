package com.kakao.cafe.service;

import com.kakao.cafe.repository.JdbcUserRepository;
import com.kakao.cafe.repository.Repository;

public class AuthService {

    private final JdbcUserRepository jdbcUserRepository;

    public AuthService(JdbcUserRepository jdbcUserRepository) {
        this.jdbcUserRepository = jdbcUserRepository;
    }
}
