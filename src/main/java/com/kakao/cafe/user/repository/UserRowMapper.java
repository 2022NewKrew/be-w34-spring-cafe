package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(rs.getString("userid"),
                                rs.getString("password"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getLong("sequence"));

        return user;
    }
}
