package com.kakao.cafe.domain.user;

import com.kakao.cafe.interfaces.common.UserDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-20T18:43:08+0900",
    comments = "version: 1.5.0.Beta2, compiler: javac, environment: Java 11.0.13 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.userId( userDto.getUserId() );
        user.email( userDto.getEmail() );
        user.nickname( userDto.getNickname() );
        user.password( userDto.getPassword() );

        return user.build();
    }
}
