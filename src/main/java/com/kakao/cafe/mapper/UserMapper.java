package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserFormDto;
import com.kakao.cafe.dto.UserViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User         convertToEntity(UserFormDto userFormDto);
    UserViewDto convertToUserViewDto(User user);
}
