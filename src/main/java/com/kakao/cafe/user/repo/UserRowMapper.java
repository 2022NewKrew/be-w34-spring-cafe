package com.kakao.cafe.user.repo;

import com.kakao.cafe.common.BaseEntityRowMapper;
import com.kakao.cafe.user.model.User;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements BaseEntityRowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(rs.getString("USER_ID"),
                null,
                rs.getString("NAME"),
                rs.getString("EMAIL"));
        user.setHashedPassword(rs.getString("PASSWORD"));
        mapBaseProperties(user, rs);
        return user;
    }
}
