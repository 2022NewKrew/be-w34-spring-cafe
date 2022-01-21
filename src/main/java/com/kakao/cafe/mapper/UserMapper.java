package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserFormDto;
import com.kakao.cafe.dto.UserNoPwdDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserNoPwdDto convertToUserNoPwdDto(User user);
    User         convertToEntity(UserFormDto userFormDto);
}
