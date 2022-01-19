package com.kakao.cafe.Repository;

import com.kakao.cafe.Dto.User.UserCreateRequestDto;
import com.kakao.cafe.Dto.User.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper = new UserMapper();

    public List<UserResponseDto> findAll() {
        String sql = "SELECT ID, USERID, NICKNAME, EMAIL FROM USERS";

        return jdbcTemplate.query(sql, userMapper);
    }

    public void insert(UserCreateRequestDto user) {
        String sql = "INSERT INTO USERS(USERID, NICKNAME, EMAIL, PASSWORD) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                user.getUserId(), user.getNickname(), user.getEmail(), user.getPassword());
    }

    public UserResponseDto findById(Long id) {
        String sql = "SELECT ID, USERID, NICKNAME, EMAIL FROM USERS WHERE ID = ?";

        return jdbcTemplate.queryForObject(sql, userMapper, id);
    }

    private static class UserMapper implements RowMapper<UserResponseDto> {
        @Override
        public UserResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new UserResponseDto(
                    rs.getLong("ID"),
                    rs.getString("USERID"),
                    rs.getString("NICKNAME"),
                    rs.getString("EMAIL")
            );
        }
    }
}
