package com.kakao.cafe.user;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by melodist
 * Date: 2022-01-12 012
 * Time: 오후 6:22
 */
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getInt("ID"),
                rs.getString("USER_ID"),
                rs.getString("PASSWORD"),
                rs.getString("NAME"),
                rs.getString("EMAIL"));
    }
}
