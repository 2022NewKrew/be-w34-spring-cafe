package com.kakao.cafe.login.adapter.out.persistence;

import com.kakao.cafe.login.application.port.out.LoadLoginInfoPort;
import com.kakao.cafe.login.domain.LoginUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class LoginRepository implements LoadLoginInfoPort {
    private static final String FIND_LOGIN_INFO_BY_NICKNAME = "SELECT id, created_time, nickname, password FROM users WHERE nickname = ?";
    private final JdbcTemplate jdbcTemplate;

    public LoginRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<LoginUser> findLoginInfo(String nickname) {
        return jdbcTemplate.query(FIND_LOGIN_INFO_BY_NICKNAME, loginInfoRowMapper(), nickname)
                .stream().findAny();
    }

    private RowMapper<LoginUser> loginInfoRowMapper() {
        return ((rs, rowNum) -> new LoginUser(rs.getLong("id"),
                rs.getTimestamp("created_time").toLocalDateTime(),
                rs.getString("nickname"),
                rs.getString("password"))
        );
    }
}
