package com.kakao.cafe;


import com.kakao.cafe.web.DbRepository;
import com.kakao.cafe.web.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.*;


@WebMvcTest
public class RepositoryTest {

    @Autowired
    DbRepository dbRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void saveUser() {
        User u = new User("test@test.com", "teddy");
        User newUser = dbRepository.saveUser(u);
        assertThat(newUser.getId()).isGreaterThan(0);
        assertThat(u).isEqualTo(newUser);
    }
}
