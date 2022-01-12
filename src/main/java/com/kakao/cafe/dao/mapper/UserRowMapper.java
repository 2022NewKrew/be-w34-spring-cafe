package com.kakao.cafe.dao.mapper;

import com.kakao.cafe.vo.UserVo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserVo> {
    @Override
    public UserVo mapRow(ResultSet rs, int rowNum) throws SQLException {
        String userId = rs.getString("userId");
        String password = rs.getString("password");
        String name = rs.getString("name");
        String email = rs.getString("email");
        return new UserVo(userId,password,name,email);
    }
}
