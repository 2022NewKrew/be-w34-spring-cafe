package com.kakao.cafe.domain.user;

import com.kakao.cafe.interfaces.common.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper(componentModel = "spring")
public interface UserMapper extends RowMapper<User> {
    @Mapping(target = "id", ignore = true)
    User toEntity(UserDto userDto);

    @Override
    default User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .email(rs.getString("email"))
                .nickname(rs.getString("nickname"))
                .password(rs.getString("password"))
                .build();
    }
}
