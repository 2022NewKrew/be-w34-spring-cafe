package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserH2DAO implements UserDAOInterface {
    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper = new UserMapper();

    @Autowired
    public UserH2DAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO USERLIST VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                user.getId(),
                user.getUserId(),
                user.getPassword(),
                user.getName(),
                user.getEmail());
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT ID, USERID, PASSWORD, NAME, EMAIL FROM USERLIST";
        return jdbcTemplate.query(sql, userMapper);
    }

    @Override
    public User findById(String userId) {
        String sql = "SELECT ID, USERID, PASSWORD, NAME, EMAIL FROM USERLIST WHERE USERID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, userMapper, userId);
        } catch (
                EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getLong("ID"),
                    rs.getString("USERID"),
                    rs.getString("PASSWORD"),
                    rs.getString("NAME"),
                    rs.getString("EMAIL")
            );
        }
    }
}
