package com.kakao.cafe.user.repository.mapper;

import com.kakao.cafe.user.persistence.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("id");
        String userId = rs.getString("user_id");
        String password = rs.getString("password");
        String name = rs.getString("name");
        String email = rs.getString("email");
        return User.of(id, userId, password, name, email);
    }
}
