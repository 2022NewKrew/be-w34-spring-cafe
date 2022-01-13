package com.kakao.cafe.user.repository.mapper;

import com.kakao.cafe.user.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(rs.getLong("id"),
                rs.getString("user_string_id"),
                rs.getString("email"),
                rs.getString("name"),
                rs.getString("password"),
                rs.getTimestamp("sign_up_date").toLocalDateTime());

        return user;
    }
}
