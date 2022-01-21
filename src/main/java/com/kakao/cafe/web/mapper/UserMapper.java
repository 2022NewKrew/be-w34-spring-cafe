package com.kakao.cafe.web.mapper;

import com.kakao.cafe.domain.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<Users> {
    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        Users user = new Users();

        user.setId(rs.getInt("id"));
        user.setUserId(rs.getString("userid"));
        user.setPassword(rs.getString("password"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));

        return user;
    }
}
