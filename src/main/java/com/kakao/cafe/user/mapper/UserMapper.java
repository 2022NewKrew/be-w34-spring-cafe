package com.kakao.cafe.user.mapper;

import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.presentation.dto.JoinRequest;
import com.kakao.cafe.user.presentation.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User user){
        return new UserDto(user.getUserId(), user.getName(), user.getEmail());
    }

    public User toUser(JoinRequest joinRequestDto){
        return new User(joinRequestDto.getUserId(), joinRequestDto.getPassword()
                , joinRequestDto.getName(), joinRequestDto.getEmail());
    }
}
