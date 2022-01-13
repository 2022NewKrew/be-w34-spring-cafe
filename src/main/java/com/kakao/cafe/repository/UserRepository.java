package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.dto.UserSignUpDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(UserSignUpDto user) throws SQLException {
        String sql = "insert into MEMBER values(?,?,?,?)"; // sysdate
        int result = jdbcTemplate.update(sql,
                user.getUserId(),
                user.getEmail(),
                user.getName(),
                user.getPassword()
        );

        if (result != 1)
            throw new SQLException("User insertion fail.");
    }

    public User findById(String userId) {
        return jdbcTemplate.queryForObject(
                "select * from MEMBER where userId = ?",
                new UserMapper(),
                userId
        );
    }

    public User findByName(String name) {
        return jdbcTemplate.queryForObject(
                "select * from MEMBER where name = ?",
                new UserMapper(),
                name
        );
    }

    public List<User> findAll() {
        return jdbcTemplate.query(
                "select * from MEMBER",
                new UserMapper()
        );
    }

    public class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int count) throws SQLException {

            return new User(
                    rs.getString("userId"),
                    rs.getString("email"),
                    rs.getString("name"),
                    rs.getString("password")
            );
        };
    }
}
