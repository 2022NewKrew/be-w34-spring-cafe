package com.kakao.cafe.repository;

import com.kakao.cafe.config.JdbcConfig;
import com.kakao.cafe.config.SecurityConfig;
import com.kakao.cafe.dto.UserRegistrationDto;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.util.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JdbcUserRepositoryTest {

    private final String userId = "testUserId";
    private final String password = "testPassword";
    private final String email = "testEmail";
    private final String userId2 = "testUserId2";
    private final String password2 = "testPassword2";
    private final String email2 = "testEmail2";
    private final String exampleId = "'chen'";
    private final String examplePassword = "'1234'";
    private final String exampleEmail = "'chen.kim@kakaocorp.com'";

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
        JdbcUserRepository jdbcUserRepository = new JdbcUserRepository(new JdbcTemplate(new JdbcConfig().dataSource()), new UserMapper(), new SecurityConfig().passwordEncoder());
        User user = new User(userId, password, email);
        User user2 = new User(userId2, password2, email2);

        // when
        jdbcUserRepository.createUser(user);
        jdbcUserRepository.createUser(user2);

        // then
        List<User> users = jdbcUserRepository.readUsers();
        Assertions.assertEquals(3, users.size());
    }

    @Test
    void readUser() {
    }
}