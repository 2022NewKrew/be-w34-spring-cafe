package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.user.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(new UserId(rs.getString("USERID")));
        user.setPassword(new Password(rs.getString("PASSWORD")));
        user.setName(new Name(rs.getString("NAME")));
        user.setEmail(new Email(rs.getString("EMAIL")));

        return user;
    }
}
