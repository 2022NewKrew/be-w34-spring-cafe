package com.kakao.cafe.mapper;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "registeredDate", expression = "java(java.time.LocalDateTime.now())")
    UserEntity toUserEntity(UserDto userDto);

    @Mapping(source = "registeredDate", target = "registeredDate", dateFormat = "yyyy-MM-dd")
    UserDto toUserDto(UserEntity userEntity);
}
