package com.kakao.cafe.Repository;

import com.kakao.cafe.Dto.Login.LoginAuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class LoginDao {
    private final JdbcTemplate jdbcTemplate;
    private final LoginMapper loginMapper = new LoginMapper();

    public LoginAuthDto findByEmail(String email) {
        String sql = "SELECT ID, EMAIL, PASSWORD FROM USERS WHERE EMAIL = ?";

        return jdbcTemplate.queryForObject(sql, loginMapper, email);
    }

    private static class LoginMapper implements RowMapper<LoginAuthDto> {
        @Override
        public LoginAuthDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new LoginAuthDto(
                    rs.getLong("ID"),
                    rs.getString("EMAIL"),
                    rs.getString("PASSWORD")
            );
        }
    }

}
