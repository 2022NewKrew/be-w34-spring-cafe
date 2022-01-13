package com.kakao.cafe.repository.user.mapper;

import com.kakao.cafe.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .userId(rs.getString("user_id"))
                .password(rs.getString("password"))
                .userName(rs.getString("user_name"))
                .email(rs.getString("email"))
                .build();
    }
}
