package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.user.JdbcUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Sql(scripts = {"classpath:/sql/schema.sql"})
class UsersRepositoryTest {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Test
    void signup() {
        // given
        User user = User.of("1", "1", "1", "1");

        // when
        JdbcUserRepository usersRepository = new JdbcUserRepository(jdbcTemplate);
        Long savedUserId = usersRepository.insertUser(user);

        // then
        assertThat(savedUserId).isGreaterThan(0L);
    }
}