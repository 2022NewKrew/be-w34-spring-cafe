package com.kakao.cafe.users;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcUserRepositoryTest {
    @Autowired
    JdbcUserRepository jdbcUserRepository;

    List<User> users = List.of(
            new User("1234", "1234", "1234", "1234"),
            new User("asdf", "asdf", "asdf", "asdf")
    );

    @Test
    void 저장() {
        User given = users.get(0);

        User actual = jdbcUserRepository.save(given);

        Assertions.assertThat(given).isEqualTo(actual);
    }

    @Test
    void 유저찾기() {
        User given = users.get(0);
        jdbcUserRepository.save(given);

        User actual = jdbcUserRepository.findByUserId(given.getUserId()).get();

        Assertions.assertThat(given.getUserId()).isEqualTo(actual.getUserId());
        Assertions.assertThat(given.getName()).isEqualTo(actual.getName());
        Assertions.assertThat(given.getPassword()).isEqualTo(actual.getPassword());
        Assertions.assertThat(given.getEmail()).isEqualTo(actual.getEmail());
    }

    @Test
    void findAll() {
        jdbcUserRepository.save(users.get(0));
        jdbcUserRepository.save(users.get(1));

        List<User> actual = jdbcUserRepository.findAll();

        Assertions.assertThat(actual).hasSize(2);
    }
}