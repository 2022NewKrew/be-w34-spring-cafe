package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getLong("user_seq_id"))
                .userId(rs.getString("user_id"))
                .password(rs.getString("password"))
                .name(rs.getString("name"))
                .email(rs.getString("email"))
                .build();
    }
}
