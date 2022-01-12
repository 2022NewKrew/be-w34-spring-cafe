package com.kakao.cafe.interfaces.user.dto;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserVo;
import com.kakao.cafe.interfaces.user.dto.request.JoinUserRequestDto;
import com.kakao.cafe.interfaces.user.dto.request.UpdateUserRequestDto;
import com.kakao.cafe.interfaces.user.dto.response.UserResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    private UserMapper() {
    }

    public static UserVo convertJoinUserDtoToVo(JoinUserRequestDto joinUserRequestDto) {
        return new UserVo(joinUserRequestDto.getUserId(),
                joinUserRequestDto.getPassword(),
                joinUserRequestDto.getName(),
                joinUserRequestDto.getEmail());
    }

    public static UserVo convertUpdateUserDtoToVo(String userId, UpdateUserRequestDto updateUserRequestDto) {
        return new UserVo(userId,
                updateUserRequestDto.getChangePassword(),
                updateUserRequestDto.getName(),
                updateUserRequestDto.getEmail());
    }

    public static UserResponseDto convertEntityToDto(User user) {
        return new UserResponseDto(user.getUserId(), user.getName(), user.getEmail());
    }

    public static List<UserResponseDto> convertEntityListToResponseDtoList(List<User> userList) {
        return userList.stream()
                .map(UserMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }
}
