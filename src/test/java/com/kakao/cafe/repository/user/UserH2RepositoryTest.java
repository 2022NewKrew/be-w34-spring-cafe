package com.kakao.cafe.repository.user;

import com.kakao.cafe.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Map;

import static com.kakao.cafe.constant.sql.UserSql.FIND_BY_EMAIL;

@SpringBootTest
class UserH2RepositoryTest {
    NamedParameterJdbcTemplate jdbcTemplate;
    SimpleJdbcInsert simpleJdbcInsert;
    RowMapper<UserEntity> rowMapper;

    @Autowired
    UserH2RepositoryTest(DataSource dataSource, UserEntityRowMapper rowMapper) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
        this.rowMapper = rowMapper;
    }

    @Test
    @DisplayName("email 찾기 실패 테스트")
    void findByEmailFailTest() {
        //given
        String email = "afaf@afaf";
        Map<String, String> params = Collections.singletonMap("email", email);

        //when
        //then
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            jdbcTemplate.queryForObject(FIND_BY_EMAIL.getQuery(), params, rowMapper);
        });
    }
}
