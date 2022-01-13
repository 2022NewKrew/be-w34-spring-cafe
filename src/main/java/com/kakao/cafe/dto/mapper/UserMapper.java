package com.kakao.cafe.dto.mapper;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.user.UserResponseDto;
import com.kakao.cafe.dto.user.UserSaveDto;
import com.kakao.cafe.dto.user.UserUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDto toDto(User user);

    User toEntityFromSaveDto(UserSaveDto userSaveDto);

    User toEntityFromUpdateDto(UserUpdateDto userUpdateDto);

    List<UserResponseDto> toDtoList(List<User> users);
}
