package com.kakao.cafe.web.user;

import com.kakao.cafe.web.user.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("ID"));
        user.setUserId(rs.getString("USERID"));
        user.setEmail(rs.getString("EMAIL"));
        user.setName(rs.getString("NAME"));
        user.setPassword(rs.getString("PASSWORD"));

        return user;
    }
}
