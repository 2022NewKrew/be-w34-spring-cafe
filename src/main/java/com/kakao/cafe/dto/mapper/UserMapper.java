package com.kakao.cafe.dto.mapper;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.user.UserRequestDto;
import com.kakao.cafe.dto.user.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface UserMapper{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDto toDto(User user);

    User toEntity(UserRequestDto userRequestDto);

    List<UserResponseDto> toDtoList(List<User> users);
}
