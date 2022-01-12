package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring", uses= UserService.class)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toEntity(UserDto user);
}
