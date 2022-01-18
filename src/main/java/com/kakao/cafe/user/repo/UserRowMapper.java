package com.kakao.cafe.user.repo;

import com.kakao.cafe.user.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(rs.getString("USER_ID"),
                null,
                rs.getString("NAME"),
                rs.getString("EMAIL"));
        user.setId(rs.getLong("ID"));
        user.setHashedPassword(rs.getString("PASSWORD"));
        return user;
    }
}
