package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.CreateUserDto;
import com.kakao.cafe.dto.FindUserDto;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserUseCase {

    UUID join(CreateUserDto createUserDto);

    List<User> getAll();

    Optional<User> findById(FindUserDto findUserDto);

}
