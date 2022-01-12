package com.kakao.cafe.module.repository.mapper;

import com.kakao.cafe.module.model.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDBMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String userId = rs.getString("user_id");
        String password = rs.getString("password");
        String name = rs.getString("name");
        String email = rs.getString("email");
        return new User(id, userId, password, name, email);
    }
}
