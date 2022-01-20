package com.kakao.cafe.dao;

import com.kakao.cafe.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(User user) {
        final String sql = "INSERT INTO USER_TABLE (email, password, nickname) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getNickname());
    }

    public List<User> findAll() {
        final String sql = "SELECT userId, email, password, nickname, createDate FROM USER_TABLE ORDER BY userId DESC";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    public boolean existsByEmailOrNickname(String email, String nickname) {
        final String sql = "SELECT COUNT(userId) FROM USER_TABLE WHERE userId = ? OR nickname = ?";

        final int userCount = jdbcTemplate.queryForObject(sql, Integer.class, email, nickname);

        if (userCount > 0) {
            return true;
        }

        return false;
    }
    
    public User findByUserId(long userId) {
        final String sql = "SELECT userId, email, password, nickname, createDate FROM USER_TABLE WHERE userId = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), userId);
    }

    public User findByEmailAndPassword(String email, String password) {
        final String sql = "SELECT userId, email, password, nickname, createDate FROM USER_TABLE WHERE email = ? AND password = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), email, password);
    }

    private class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();

            user.setUserId(rs.getLong("userId"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setNickname(rs.getString("nickname"));
            user.setCreateDate(rs.getTimestamp("createDate").toLocalDateTime());

            return user;
        }
    }
}
