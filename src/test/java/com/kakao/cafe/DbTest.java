package com.kakao.cafe;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class DbTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void notNull() {
        assertThat(jdbcTemplate).isNotNull();
    }

    @Test
    void count() {
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM `user`", Integer.class);
        assertThat(count).isGreaterThanOrEqualTo(3);
    }

}
