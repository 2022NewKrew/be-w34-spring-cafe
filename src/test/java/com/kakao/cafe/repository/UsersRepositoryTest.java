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
@Sql(scripts = {"classpath:com/kakao/cafe/repository/schema.sql"})
class UsersRepositoryTest {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Test
    void signup() {
        // given
        User user = new User("1", "1", "1", "1@1");

        // when
        JdbcUserRepository usersRepository = new JdbcUserRepository(jdbcTemplate);
<<<<<<< HEAD
        Long savedUserId = usersRepository.insertUser(user);
=======
        Long savedUserId = usersRepository.save(user);
>>>>>>> 21a0e77 (review 사항 반영)

        // then
        assertThat(savedUserId).isGreaterThan(0L);
    }
}