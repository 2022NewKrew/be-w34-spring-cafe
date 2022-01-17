package com.kakao.cafe.repository;

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



//    @Test
//    @DisplayName("[성공] JdbcUserRepository 클래스 생성")
//    void JdbcUserRepository() {
//        new JdbcUserRepository(jdbcTemplate, userMapper, passwordEncoder);
//    }
//
//
//    @Test
//    @DisplayName("[성공] JdbcUserRepository Create")
//    void createUser() {
        // given
//        JdbcUserRepository jdbcUserRepository = new JdbcUserRepository(jdbcTemplate, userMapper, passwordEncoder);
//        User user = new User(userId, password, email);

        // when
//        jdbcUserRepository.createUser(user);

        // then

//    }

    @Test
    void readUsers() {
    }

    @Test
    void readUser() {
    }
}