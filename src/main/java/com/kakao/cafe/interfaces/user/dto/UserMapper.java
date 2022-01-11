package com.kakao.cafe.interfaces.user.dto;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.interfaces.user.dto.request.JoinUserRequestDto;
import com.kakao.cafe.interfaces.user.dto.response.UserListResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    private UserMapper() {
    }

    public static User convertJoinUserDtoToEntity(JoinUserRequestDto joinUserRequestDto) {
        return new User(joinUserRequestDto.getUserId(),
                joinUserRequestDto.getPassword(),
                joinUserRequestDto.getName(),
                joinUserRequestDto.getEmail());
    }

    public static List<UserListResponseDto> convertEntityListToResponseDtoList(List<User> userList) {
        return userList.stream()
                .map(e -> new UserListResponseDto(e.getUserId(), e.getName(), e.getEmail()))
                .collect(Collectors.toList());
    }
}
