package com.kakao.cafe.user.presentation.mapper;

import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.presentation.dto.UserDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(MappingContext<User, UserDto> context) {
        User user = context.getSource();
        return new UserDto(user.getUserId(), user.getUserInfo().getName(), user.getUserInfo().getEmail());
    }
}
