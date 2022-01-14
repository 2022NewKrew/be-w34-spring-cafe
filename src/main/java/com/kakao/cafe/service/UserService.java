package com.kakao.cafe.service;

import com.kakao.cafe.dto.*;
import com.kakao.cafe.entity.User;

public interface UserService {
    UserDto register(UserDto dto);

    AuthDto login(UserDto dto);

    UserDto getUser(AuthDto dto);

    void modify(EditUserDto dto);

    PageResultDto<UserDto, User> getList(PageRequestDto requestDto);

    default User dtoToEntity(UserDto dto) {
        return User.builder()
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

    default UserDto entityToDto(User entity) {
        return UserDto.builder()
                .email(entity.getEmail())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
    }

    default AuthDto entityToAuthDto(User entity) {
        return AuthDto.builder()
                .email(entity.getEmail())
                .username(entity.getUsername())
                .build();
    }
}
