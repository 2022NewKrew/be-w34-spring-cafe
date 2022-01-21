package com.kakao.cafe.repository.mapper;

import com.kakao.cafe.domain.user.Email;
import com.kakao.cafe.domain.user.Name;
import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserName;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        UUID id = UUID.fromString(rs.getString("users_id"));
        UserName userName = new UserName(rs.getString("username"));
        Password password = new Password(rs.getString("password"));
        Name name = new Name(rs.getString("name"));
        Email email = new Email(rs.getString("email"));
        return new User(id, userName, password, name, email);
    }
}
