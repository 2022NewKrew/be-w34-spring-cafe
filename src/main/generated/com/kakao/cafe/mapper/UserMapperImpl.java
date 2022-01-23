package com.kakao.cafe.mapper;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.dto.UserDto.UserDtoBuilder;
import com.kakao.cafe.entity.UserEntity;
import com.kakao.cafe.entity.UserEntity.UserEntityBuilder;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-21T17:17:49+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toUserEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.id( userDto.getId() );
        userEntity.email( userDto.getEmail() );
        userEntity.nickName( userDto.getNickName() );
        userEntity.password( userDto.getPassword() );

        userEntity.registeredDate( java.time.LocalDateTime.now() );

        return userEntity.build();
    }

    @Override
    public UserDto toUserDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        if ( userEntity.getRegisteredDate() != null ) {
            userDto.registeredDate( DateTimeFormatter.ofPattern( "yyyy-MM-dd" ).format( userEntity.getRegisteredDate() ) );
        }
        userDto.id( userEntity.getId() );
        userDto.email( userEntity.getEmail() );
        userDto.nickName( userEntity.getNickName() );
        userDto.password( userEntity.getPassword() );

        return userDto.build();
    }

    @Override
    public List<UserDto> toUserDtoList(List<UserEntity> userEntityList) {
        if ( userEntityList == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( userEntityList.size() );
        for ( UserEntity userEntity : userEntityList ) {
            list.add( toUserDto( userEntity ) );
        }

        return list;
    }
}
