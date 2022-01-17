package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper()
public interface UserMapper extends RowMapper<User>{

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Mapping(target = "id", ignore = true)
    User toEntity(UserDto user);

    @Override
    default User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder().
                id(rs.getLong("id")).
                userId(rs.getString("userId")).
                password(rs.getString("password")).
                name(rs.getString("name")).
                email(rs.getString("email")).
                time(rs.getString("time")).
                build();
    }

}
