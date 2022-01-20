package com.kakao.cafe.global.mapper;

import com.kakao.cafe.user.dto.request.SignUpReq;
import com.kakao.cafe.user.dto.response.UserDto;
import com.kakao.cafe.user.persistence.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", constant = "0L")
    User signUpReqToEntity(SignUpReq req);

    UserDto userToDto(User user);
}
