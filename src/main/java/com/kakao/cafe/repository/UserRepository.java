package com.kakao.cafe.repository;

import com.kakao.cafe.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    UserDto save(UserDto userDto);
    Optional<UserDto> findByUserId(String userId);
    List<UserDto> findAll();
}
