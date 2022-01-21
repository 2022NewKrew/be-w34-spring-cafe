package com.kakao.cafe.domain.login;

import com.kakao.cafe.domain.login.dto.UserLogin;
import com.kakao.cafe.domain.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class LoginRepository {

    private final JdbcTemplate jdbcTemplate;

    public LoginRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Optional<User> login(UserLogin userLogin) {
        List<User> result = jdbcTemplate.query("select * from users where userId=? and password =?", userRowMapper(), userLogin.getUserId(), userLogin.getPassword());
        return result.stream().findAny();
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUserId(rs.getString("userId"));
            user.setEmail(rs.getString("email"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            return user;
        };
    }
}
