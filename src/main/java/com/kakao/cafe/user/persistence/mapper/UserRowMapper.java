package com.kakao.cafe.user.persistence.mapper;


import com.kakao.cafe.user.domain.entity.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        String userId = rs.getString("userId");
        String password = rs.getString("password");
        String name = rs.getString("name");
        String email = rs.getString("email");

        return new User(userId, password, name, email);
    }
}
