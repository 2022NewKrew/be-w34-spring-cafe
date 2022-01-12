package com.kakao.cafe.domain.user;

import com.kakao.cafe.interfaces.common.UserDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-12T22:51:42+0900",
    comments = "version: 1.5.0.Beta2, compiler: javac, environment: Java 11.0.13 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Long id = null;
        String email = null;
        String nickname = null;
        String password = null;

        id = arg0.getId();
        email = arg0.getEmail();
        nickname = arg0.getNickname();
        password = arg0.getPassword();

        UserDto userDto = new UserDto( id, email, nickname, password );

        return userDto;
    }

    @Override
    public User toEntity(UserDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        User user = new User();

        user.setId( arg0.getId() );
        user.setEmail( arg0.getEmail() );
        user.setNickname( arg0.getNickname() );
        user.setPassword( arg0.getPassword() );

        return user;
    }

    @Override
    public List<UserDto> toDtoList(List<User> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( arg0.size() );
        for ( User user : arg0 ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public List<User> toEntityList(List<UserDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( arg0.size() );
        for ( UserDto userDto : arg0 ) {
            list.add( toEntity( userDto ) );
        }

        return list;
    }

    @Override
    public void updateFromDto(UserDto arg0, User arg1) {
        if ( arg0 == null ) {
            return;
        }

        if ( arg0.getId() != null ) {
            arg1.setId( arg0.getId() );
        }
        if ( arg0.getEmail() != null ) {
            arg1.setEmail( arg0.getEmail() );
        }
        if ( arg0.getNickname() != null ) {
            arg1.setNickname( arg0.getNickname() );
        }
        if ( arg0.getPassword() != null ) {
            arg1.setPassword( arg0.getPassword() );
        }
    }
}
