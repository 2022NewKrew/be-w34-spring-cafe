package com.kakao.cafe.repository.mapper;

import com.kakao.cafe.domain.user.Profile;
import com.kakao.cafe.domain.user.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String userId = rs.getString("user_id");
        String password = rs.getString("password");
        String name = rs.getString("name");
        String email = rs.getString("email");
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();
        User user = new User(userId, password, new Profile(name, email), createdAt, updatedAt);
        user.setId(id);
        return user;
    }
}
