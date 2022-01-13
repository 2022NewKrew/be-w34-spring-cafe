package com.kakao.cafe.domain.user;

import com.kakao.cafe.interfaces.common.UserDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-13T18:26:24+0900",
    comments = "version: 1.5.0.Beta2, compiler: javac, environment: Java 11.0.13 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        if ( rs == null ) {
            return null;
        }

        Object object = new Object();

        return object;
    }

    @Override
    public User toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( userDto.getEmail() );
        user.nickname( userDto.getNickname() );
        user.password( userDto.getPassword() );

        return user.build();
    }
}
