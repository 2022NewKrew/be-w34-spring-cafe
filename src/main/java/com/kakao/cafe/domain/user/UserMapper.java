package com.kakao.cafe.domain.user;

import com.kakao.cafe.interfaces.common.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDto userDto);
}
