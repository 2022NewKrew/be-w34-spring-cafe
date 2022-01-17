package com.kakao.cafe.user.repository.mapper;

import com.kakao.cafe.user.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = User.builder()
                .id(rs.getLong("id"))
                .stringId(rs.getString("string_id"))
                .email(rs.getString("email"))
                .name(rs.getString("name"))
                .password(rs.getString("password"))
                .signUpDate(rs.getTimestamp("sign_up_date").toLocalDateTime())
                .build();

        return user;
    }
}
