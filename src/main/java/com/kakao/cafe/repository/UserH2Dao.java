package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Primary
public class UserH2Dao implements UserDAOInterface {
    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper = new UserMapper();
    private final Logger logger = LoggerFactory.getLogger(UserH2Dao.class);

    @Autowired
    public UserH2Dao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection con) -> {
            PreparedStatement pstmt = con.prepareStatement(
                    "INSERT INTO USERLIST(USERID, PASSWORD, NAME, EMAIL) VALUES (?, ?, ?, ?)",
                    new String[]{"ID"}
            );
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
            return pstmt;
        }, keyHolder);
        logger.info(String.valueOf(keyHolder.getKey()) + "번째 유저 생성");
    }

    @Override
    public void editUser(String userId, String name, String email) {
        String sql = "UPDATE USERLIST SET NAME=?, EMAIL=? WHERE USERID=?";
        jdbcTemplate.update(sql, name, email, userId);
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
