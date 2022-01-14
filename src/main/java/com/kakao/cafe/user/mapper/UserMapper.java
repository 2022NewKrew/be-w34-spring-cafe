package com.kakao.cafe.user.mapper;

import com.kakao.cafe.user.dto.request.UserCreateRequest;
import com.kakao.cafe.user.dto.response.UserInfoResponse;
import com.kakao.cafe.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(target = "id", constant = "0L"),
            @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    })
    User userCreateRequestToEntity(UserCreateRequest req);
    UserInfoResponse userToUserInfoResponse(User user);
}
