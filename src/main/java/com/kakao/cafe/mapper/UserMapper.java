package com.kakao.cafe.mapper;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class UserMapper {
    public UserEntity toUserEntity(UserDto userDto) {
        return UserEntity.builder()
                .email(userDto.getEmail())
                .nickName(userDto.getNickName())
                .password(userDto.getPassword())
                .build();
    }

    public UserDto toUserDto(UserEntity userEntity) {
        return UserDto.builder()
                .email(userEntity.getEmail())
                .nickName(userEntity.getNickName())
                .registeredDate(userEntity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    }
}
