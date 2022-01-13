package com.kakao.cafe.domain.user;

import com.kakao.cafe.interfaces.common.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.jdbc.core.RowMapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends RowMapper {
    @Mapping(target = "id", ignore = true)
    User toEntity(UserDto userDto);
}
