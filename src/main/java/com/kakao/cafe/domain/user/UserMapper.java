package com.kakao.cafe.domain.user;

import com.kakao.cafe.interfaces.common.GenericMapper;
import com.kakao.cafe.interfaces.common.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<UserDto, User> {
}
