package com.kakao.cafe.repository;

import com.kakao.cafe.config.SecurityConfig;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.util.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserMapper userMapper;

    @Test
    @DisplayName("[성공] JdbcUserRepository 클래스 생성")
    void JdbcUserRepository() {
        new JdbcUserRepository(jdbcTemplate, userMapper, new SecurityConfig().passwordEncoder());
    }


    @Test
    @DisplayName("[성공] JdbcUserRepository 유저 생성")
    void createUser() {
        // given
        JdbcUserRepository jdbcUserRepository = new JdbcUserRepository(jdbcTemplate, userMapper, new SecurityConfig().passwordEncoder());
        User user = new User(userId, password, email);

        // when & then
        jdbcUserRepository.createUser(user);
    }

    @Test
    @DisplayName("[성공] JdbcUserRepository 유저 전체 조회")
    void readUsers() {
        JdbcUserRepository jdbcUserRepository = new JdbcUserRepository(jdbcTemplate, userMapper, new SecurityConfig().passwordEncoder());
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
    @DisplayName("[성공] JdbcUserRepository 유저 단일 조회")
    void readUser() {
        JdbcUserRepository jdbcUserRepository = new JdbcUserRepository(jdbcTemplate, userMapper, new SecurityConfig().passwordEncoder());
        User user = new User(userId, password, email);
        User user2 = new User(userId2, password2, email2);

        // when
        jdbcUserRepository.createUser(user);
        jdbcUserRepository.createUser(user2);

        // then
        Optional<User> answer = jdbcUserRepository.readUser(userId2);
        Assertions.assertEquals("testUserId2",
                answer.orElseThrow(() -> new RuntimeException("userId가 null입니다")).getUserId());
    }
}