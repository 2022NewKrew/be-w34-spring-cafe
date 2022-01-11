package com.kakao.cafe.repository;

import com.kakao.cafe.dto.UserResponseDto;
import com.kakao.cafe.dto.UserRequestDto;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    UserResponseDto save(UserRequestDto userRequestDto);
    Optional<UserResponseDto> findByUserId(String userId);
    List<UserResponseDto> findAll();
}
