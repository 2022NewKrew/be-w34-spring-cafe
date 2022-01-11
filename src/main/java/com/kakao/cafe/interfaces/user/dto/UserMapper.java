package com.kakao.cafe.interfaces.user.dto;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import com.kakao.cafe.interfaces.user.dto.request.UserDto;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    private UserMapper() {}

    public static User convertUserDtoToEntity(UserDto userDto) {
        return new User(userDto.getUserId(),
                userDto.getPassword(),
                userDto.getName(),
                userDto.getEmail());
    }

    public static List<UserDto> convertUserEntityListToDtoList(List<User> userList) {
        return userList.stream()
                .map(e -> new UserDto(e.getUserId(), e.getPassword(), e.getName(), e.getEmail()))
                .collect(Collectors.toList());
    }
}
