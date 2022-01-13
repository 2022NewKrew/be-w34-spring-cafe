package com.kakao.cafe.repository;

import com.kakao.cafe.domain.dto.UserCreateCommand;
import com.kakao.cafe.domain.dto.UserModifyCommand;
import com.kakao.cafe.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class UserJdbcRepository implements UserRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void store(UserCreateCommand ucc) {
        String sql= "INSERT INTO USERS(USER_ID, PASSWORD, NAME, EMAIL) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                ucc.getUserId(),
                ucc.getPassword(),
                ucc.getName(),
                ucc.getEmail());
    }

    @Override
    public void modify(String userId, UserModifyCommand umc) {
        String sql = "UPDATE USERS SET PASSWORD=?, NAME=?, EMAIL=? WHERE USER_ID=?";
        jdbcTemplate.update(sql,
                umc.getPassword(),
                umc.getName(),
                umc.getEmail(),
                userId);
    }

    @Override
    public void delete(String userId) {
        String sql = "DELETE FROM USERS WHERE USER_ID=?";
        jdbcTemplate.update(sql, userId);
    }

    @Override
    public User search(String userId) {
        String sql = "SELECT * FROM USERS WHERE USER_ID=?";
        return jdbcTemplate.queryForObject(sql, userRowMapper(), userId);
    }

    @Override
    public List<User> getAllUser() {
        String sql = "SELECT * FROM USERS";
        return jdbcTemplate.query(sql, userRowMapper());
    }

    public RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User(
                rs.getLong("USER_INDEX"),
                rs.getString("USER_ID"),
                rs.getString("PASSWORD"),
                rs.getString("NAME"),
                rs.getString("EMAIL")
        );
    }
}
