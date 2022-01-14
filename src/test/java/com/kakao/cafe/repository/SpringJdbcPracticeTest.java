package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.mapper.UserMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

@DataJdbcTest
@Sql("classpath:script.sql")
class SpringJdbcPracticeTest {

    @Autowired
    JdbcTemplate jdbcTemplate;
    UserMapper userMapper = new UserMapper();

    @Test
    @DisplayName("아이디로 회원 조회")
    void testOfFindUserByUserId() {
        jdbcTemplate.update("insert into USERS(user_id,password,name,email) values (?,?,?,?)", "leaf", "1234", "김남현", "leaf.hyeon@kakaocorp.com");
        User user = jdbcTemplate.queryForObject("select * from USERS where user_id=?", userMapper, "leaf");

        Assertions.assertThat(user.getUserId()).isEqualTo("leaf");
    }

}