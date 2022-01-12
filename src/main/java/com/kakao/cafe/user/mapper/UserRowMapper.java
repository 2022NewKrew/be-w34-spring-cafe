package com.kakao.cafe.user.mapper;

import com.kakao.cafe.user.User;
<<<<<<< HEAD
import com.kakao.cafe.user.UserStatus;
=======
>>>>>>> 8a564e5 (3일차구현 (#190))
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUserId(rs.getString("user_id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
<<<<<<< HEAD
        user.setRole(UserStatus.valueOf(rs.getString("role")));
=======
>>>>>>> 8a564e5 (3일차구현 (#190))

        return user;
    }
}
