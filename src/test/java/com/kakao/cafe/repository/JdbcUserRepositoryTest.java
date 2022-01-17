package com.kakao.cafe.repository;

import com.kakao.cafe.config.JdbcConfig;
import com.kakao.cafe.config.SecurityConfig;
import com.kakao.cafe.dto.UserRegistrationDto;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.util.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class JdbcUserRepositoryTest {

    private final String userId = "testUserId";
    private final String password = "testPassword";
    private final String email = "testEmail";

//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    UserMapper userMapper;


    @Test
    @DisplayName("[성공] JdbcUserRepository 클래스 생성")
    void JdbcUserRepository() {
//        new JdbcUserRepository(jdbcTemplate, userMapper, new SecurityConfig().passwordEncoder());
        new JdbcUserRepository(new JdbcTemplate(new JdbcConfig().dataSource()), new UserMapper(), new SecurityConfig().passwordEncoder());
    }


    @Test
    @DisplayName("[성공] JdbcUserRepository 유저 생성")
    void createUser() {
        // given
//        JdbcUserRepository jdbcUserRepository = new JdbcUserRepository(jdbcTemplate, userMapper, new SecurityConfig().passwordEncoder());
        JdbcUserRepository jdbcUserRepository = new JdbcUserRepository(new JdbcTemplate(new JdbcConfig().dataSource()), new UserMapper(), new SecurityConfig().passwordEncoder());
        User user = new User(userId, password, email);

        // when & then
        jdbcUserRepository.createUser(user);
    }

    @Test
    void readUsers() {
    }

    @Test
    void readUser() {
    }
}