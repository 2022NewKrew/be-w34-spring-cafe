package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.mapper.UserMapper;
import com.kakao.cafe.repository.user.SpringJdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Sql("classpath:script.sql")
class SpringJbcUserRepositoryTest {

    SpringJdbcUserRepository springJdbcUserRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        springJdbcUserRepository = new SpringJdbcUserRepository(jdbcTemplate, new UserMapper());
    }

    @Test
    @DisplayName("아이디로 회원 조회")
    void testOfFindUserByUserId() {
        User user = User.builder()
                .userId("kim")
                .password("1234")
                .name("김남현")
                .email("asdfasdf")
                .build();
        springJdbcUserRepository.save(user);
        User foundUser = springJdbcUserRepository.findByUserId("kim").get();
        assertThat(foundUser.getUserId()).isEqualTo("kim");
    }

    @Test
    @DisplayName("회원 전체 조회")
    void testOfFindUsers() {
        User user1 = User.builder()
                .userId("kim")
                .password("123")
                .name("12561265")
                .email("123123")
                .build();
        User user2 = User.builder()
                .userId("lee")
                .password("123")
                .name("12561265")
                .email("123123")
                .build();
        User user3 = User.builder()
                .userId("park")
                .password("123")
                .name("12561265")
                .email("123123")
                .build();
        springJdbcUserRepository.save(user1);
        springJdbcUserRepository.save(user2);
        springJdbcUserRepository.save(user3);

        List<User> users = springJdbcUserRepository.findAll();
        assertThat(users.size()).isEqualTo(3);
    }


}