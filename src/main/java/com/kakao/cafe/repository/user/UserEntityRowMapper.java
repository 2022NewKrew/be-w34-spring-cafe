package com.kakao.cafe.repository.user;

import com.kakao.cafe.entity.UserEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserEntityRowMapper implements RowMapper<UserEntity> {
    @Override
    public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserEntity(rs.getLong("id"), rs.getString("email"),
                rs.getString("nickname"), rs.getString("password"),
                rs.getTimestamp("registered_date").toLocalDateTime());
    }
}
