package com.kakao.cafe.util;

import com.kakao.cafe.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getString("userId"),
                rs.getString("password"),
                rs.getString("email")
        );
    }
}
