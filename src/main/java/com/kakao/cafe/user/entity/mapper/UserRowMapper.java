package com.kakao.cafe.user.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kakao.cafe.user.entity.User;

@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                   .id(rs.getInt("id"))
                   .userId(rs.getString("user_id"))
                   .password(rs.getString("password"))
                   .name(rs.getString("name"))
                   .email(rs.getString("email"))
                   .build();
    }
}
